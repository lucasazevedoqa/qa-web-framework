package com.lucas.qa.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    /**
     * Construtor privado para impedir a instanciação da classe.
     * Garante que a factory seja utilizada estritamente via métodos estáticos.
     */
    private DriverFactory() {
        throw new UnsupportedOperationException("Classe utilitária de inicialização de driver não deve ser instanciada.");
    }

    /**
     * Instancia, configura e retorna um novo driver de controle do navegador Google Chrome.
     * Aplica parâmetros de execução otimizados para evitar notificações e problemas de CORS.
     * @return WebDriver configurado e com janela maximizada
     */
    public static WebDriver criarDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        return driver;
    }
}