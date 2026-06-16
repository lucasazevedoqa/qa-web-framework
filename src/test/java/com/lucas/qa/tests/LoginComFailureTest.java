package com.lucas.qa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginComFailureTest {

    @Test
    void validarLoginComSucesso() throws InterruptedException {
        // 1. Inicializar o navegador
        WebDriver driver = new ChromeDriver();

        // 2. Abre o Site
        driver.get("https://www.saucedemo.com/");

        // 3. Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        // 4. Executar a ação
        loginPage.realizaLogin("standard_user", "secret_sauce");

        // 5. Captura a mensagem de erro exibida na tela
        String mensagemErro = loginPage.obterMensagemErro();

        // 6. Verifica se a mensagem contém o texto esperado
        Assertions.assertTrue(
                mensagemErro.contains("Username and password do not match")
        );

        // 7. Pausa
        Thread.sleep(2000);

        // 8. Encerrar navegador
        driver.quit();
    }
}
