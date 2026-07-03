package com.lucas.qa.base;

import com.lucas.qa.extensions.TestResultExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@ExtendWith(TestResultExtension.class)
public class BaseTest {

    protected WebDriver driver;
    private static final String URL_ALVO = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        // Detecta se está rodando na nuvem do GitHub Actions
        boolean isCI = System.getenv("GITHUB_ACTIONS") != null;

        if (isCI) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");
        }

        // Inicialização do ecossistema do WebDriver
        this.driver = new ChromeDriver(options);

        // Só maximiza se não estiver em modo Headless (evita warnings no log)
        if (!isCI) {
            this.driver.manage().window().maximize();
        }

        this.driver.get(URL_ALVO);
    }

    /**
     * Fornece o driver ativo para componentes externos.
     * Essencial para que a TestResultExtension capture evidências antes de fechar o processo.
     */
    public WebDriver getDriver() {
        return this.driver;
    }
}