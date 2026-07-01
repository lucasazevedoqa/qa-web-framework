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
        // Configurações otimizadas para o navegador Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // Desativa pop-ups de notificação
        options.addArguments("--remote-allow-origins=*"); // Evita problemas de CORS em atualizações do Chrome

        // Inicialização do ecossistema do WebDriver
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
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