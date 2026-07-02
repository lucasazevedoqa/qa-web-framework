package com.lucas.qa.base;

import com.lucas.qa.extensions.TestResultExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor; // <--- ESSE CARA AQUI!
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

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
            options.addArguments("--headless=new"); // Roda em segundo plano sem tela
            options.addArguments("--disable-gpu");  // Desativa aceleração de hardware
            options.addArguments("--window-size=1920,1080"); // Define um tamanho de tela padrão
            options.addArguments("--no-sandbox"); // Evita problemas de permissão no Linux
            options.addArguments("--disable-dev-shm-usage"); // Evita estouro de memória
        }

        // Inicialização do ecossistema do WebDriver
        this.driver = new ChromeDriver(options);

        // REMOVIDO: implicitlyWait retirado para não conflitar com WebDriverWait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Só maximiza se não estiver em modo Headless
        if (!isCI) {
            this.driver.manage().window().maximize();
        }

        this.driver.get(URL_ALVO);

        // ... código anterior do seu BaseTest ...
        this.driver.get(URL_ALVO);

        // NOVO: Limpa completamente o cache de sessão para blindar a execução em lote no CI
        try {
            driver.manage().deleteAllCookies();
            ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
            ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        } catch (Exception e) {
            // Previne falhas caso o navegador ainda não tenha carregado o contexto JS
        }
    }

    /**
     * Fornece o driver ativo para componentes externos.
     * Essencial para que a TestResultExtension capture evidências antes de fechar o processo.
     */
    public WebDriver getDriver() {
        return this.driver;
    }
}