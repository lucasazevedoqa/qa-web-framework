package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;

public class LoginComSucessoTest extends BaseTest {

    @Test
    void validarLoginComSucesso() throws InterruptedException {

        //  Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        //  Executar a ação
        loginPage.realizaLogin(
                "standard_user",
                "secret_sauce"
        );

        //  Obtém a URL atual após login
        String urlAtual = driver.getCurrentUrl();

        //  Verificar  se o usuário foi redirecionado para a página de produtos
        Assertions.assertTrue(
                urlAtual.contains("inventory"),
                "Falha no login. Usuário não foi redirecionado para a página de produtos."
        );

    }
}
