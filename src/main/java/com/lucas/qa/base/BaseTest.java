package com.lucas.qa.base;

import com.lucas.qa.config.Config;
import com.lucas.qa.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


public class BaseTest {

    // Disponivel para todas as classes que herdarem BaseTest
    protected WebDriver driver;

    @BeforeEach
    void setup(){

        // Criar o Navegador
        driver = DriverFactory.criarDriver();

        // Acessar o Sistema
        driver.get(Config.URL_BASE);
    }

    @AfterEach
    void tearDown(){

        // Fechar o navegador após cada teste
        driver.quit();
    }

}
