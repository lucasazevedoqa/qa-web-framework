package com.lucas.qa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.drivers.DriverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class LoginComFailureTest {

    // Driver compartilhado por todos os métodos da classe
    private WebDriver driver;

    @BeforeEach
    void setup(){

        // Criar o Navegador
        driver = DriverFactory.criarDriver();

        // Acessar o Sistema
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown(){

        // Fechar o navegador após cada teste
        driver.quit();
    }

    @Test
    void validarLoginComSucesso() throws InterruptedException {

        // Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        // Executar a ação
        loginPage.realizaLogin(
                "standard_user",
                "secret_ERRO"
        );

        // Captura a mensagem de erro exibida na tela
        String mensagemErro = loginPage.obterMensagemErro();

        // Verifica se a mensagem contém o texto esperado
        Assertions.assertTrue(
                mensagemErro.contains("Username and password do not match")
        );

        // Pausa
        Thread.sleep(2000);

    }
}
