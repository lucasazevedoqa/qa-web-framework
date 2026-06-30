package com.lucas.qa.base;

import com.lucas.qa.extensions.TestResultExtension; // Importa a extension
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith; // Importa a anotação do JUnit 5
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(TestResultExtension.class) // Blinda a classe base com o observador de screenshots
public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Sua configuração atual do WebDriver (ex: ChromeOptions, localizador, etc)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    // Método essencial para a Extension conseguir herdar o driver ativo do teste
    public WebDriver getDriver() {
        return this.driver;
    }
}