package com.lucas.qa.extensions;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.utils.ScreenshotUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class TestResultExtension implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        // Opcional: Tira foto mesmo se o teste passar
        capturarEvidencia(context, "PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        // Obrigatório: Tira foto crucial se o teste falhar para sabermos onde quebrou
        capturarEvidencia(context, "FAILED");
    }

    private void capturarEvidencia(ExtensionContext context, String status) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            String nomeTeste = context.getDisplayName().replace("()", "");

            if (driver != null) {
                try {
                    ScreenshotUtils.tirarScreenshot(driver, nomeTeste, status);
                } finally {
                    // MOVEU PARA CÁ: O driver fecha IMEDIATAMENTE após tirar o screenshot
                    driver.quit();
                    System.out.println("❌ Navegador fechado pela Extension após o screenshot.");
                }
            }
        }
    }
}