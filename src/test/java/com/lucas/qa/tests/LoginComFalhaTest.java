package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;

public class LoginComFalhaTest extends BaseTest {

    @Test
    void validarLoginComSucesso() {

        // Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        // Executar a ação
        loginPage.realizaLogin(
                DadosDeTeste.USUARIO_VALIDO,
                DadosDeTeste.SENHA_INVALIDA
        );

        // Captura a mensagem de erro exibida na tela
        String mensagemErro = loginPage.obterMensagemErro();

        // Verifica se a mensagem contém o texto esperado
        Assertions.assertTrue(
                mensagemErro.contains(DadosDeTeste.MSG_ERRO_LOGIN_INVALIDO),
                "A mensagem de erro exibida na tela foi diferente da esperada"
        );

    }
}
