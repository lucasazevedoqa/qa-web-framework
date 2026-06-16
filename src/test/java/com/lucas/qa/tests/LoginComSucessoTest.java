package com.lucas.qa.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginComSucessoTest {

    @Test
    void validarLoginComSucesso() throws InterruptedException {
        // 1. Inicializar o navegador
        WebDriver driver = new ChromeDriver();

        // 2. Abre o Site
        driver.get("https://www.saucedemo.com/");

        // 3. Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        // 4. Executar a ação
        loginPage.realizaLogin("standard_user", "secret_sa123uce");

        // 5. Obtém a URL atual após login
        String urlAtual = driver.getCurrentUrl();

        // 6. Exibe a URL no console para fins de estudo
        System.out.println("URL atual: " + urlAtual);

        // 7. Verificar  se o usuário foi redirecionado para a página de produtos
        Assertions.assertTrue(
                urlAtual.contains("inventory"),
                "Falha no login. Usuário não foi redirecionado para a página de produtos."
        );

        // 8. Pausa
        Thread.sleep(3000);

        // 9. Encerrar navegador
        driver.quit();
    }
}
