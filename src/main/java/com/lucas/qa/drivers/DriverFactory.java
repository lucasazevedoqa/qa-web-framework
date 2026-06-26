package com.lucas.qa.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    // Método responsável por criar e devolver uma instância do navegador
    public static WebDriver criarDriver(){

        // Criar uma nova instância do chrome
        WebDriver driver = new ChromeDriver();

        // Maximiza a janela do navegador
        driver.manage().window().maximize();

        // Retorna o driver criado para quem chamou o método
        return driver;

    }
}
