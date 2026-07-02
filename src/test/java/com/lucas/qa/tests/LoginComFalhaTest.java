package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import com.lucas.qa.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginComFalhaTest extends BaseTest {

    @Test
    void deveExibirMensagemDeErroAoRealizarLoginComSenhaInvalida() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);

        // Act
        loginPage.realizarLogin(
                DadosDeTeste.USUARIO_VALIDO,
                DadosDeTeste.SENHA_INVALIDA
        );

        String mensagemErroAtual = loginPage.obterMensagemDeErro();

        // Assert
        Assertions.assertTrue(
                mensagemErroAtual.contains(DadosDeTeste.MSG_ERRO_LOGIN_INVALIDO),
                String.format(
                        "Mensagem de erro diferente da esperada. Esperado conter: '%s'. Atual: '%s'.",
                        DadosDeTeste.MSG_ERRO_LOGIN_INVALIDO,
                        mensagemErroAtual
                )
        );
    }
}