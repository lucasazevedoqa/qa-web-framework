package com.lucas.qa.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.lucas.qa.config.Config;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // 1. Desativa totalmente o gerenciador de senhas e preenchimento automático
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // 2. ARGUMENTOS CRÍTICOS: Desativa detecção de vazamento, notificações e automação visível
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=LeakDetection"); // Desativa especificamente o alerta de senha vazada
        options.addArguments("--disable-features=AutofillPasswordLeakDetection"); // Garante o bloqueio da checagem
        options.addArguments("--password-store=basic"); // Força a ignorar o chaveiro do sistema operacional

        // Inicializa o ChromeDriver com todas as opções
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get(Config.URL_BASE);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}