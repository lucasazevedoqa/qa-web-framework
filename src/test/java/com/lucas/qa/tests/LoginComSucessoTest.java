package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;

/**
 * Classe responsável por agrupar os cenários de testes funcionais
 * relacionados à autenticação positiva e acesso às áreas restritas do sistema.
 */
public class LoginComSucessoTest extends BaseTest {

    /**
     * Valida que o usuário é autenticado com sucesso ao fornecer credenciais válidas,
     * garantindo o redirecionamento correto para a página principal de produtos.
     */
    @Test
    void deveAutenticarEObterAcessoAPaginaDeProdutos() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                DadosDeTeste.USUARIO_VALIDO,
                DadosDeTeste.SENHA_VALIDA
        );

        ProdutosPage produtosPage = new ProdutosPage(driver);
        String tituloAtual = produtosPage.obterTituloPagina();

        Assertions.assertEquals(
                "Products",
                tituloAtual,
                "Falha no login. Usuário não foi redirecionado para a página de produtos."
        );
    }
}