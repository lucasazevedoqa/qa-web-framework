package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;

public class LoginComFailureTest extends BaseTest {

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

    }
}
