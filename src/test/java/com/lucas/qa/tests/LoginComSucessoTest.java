package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;

public class LoginComSucessoTest extends BaseTest {

    @Test
    void validarLoginComSucesso() {

        //  Criar o objeto da página (aqui conectamos o navegador a página)
        LoginPage loginPage = new LoginPage(driver);

        //  Executar a ação
        loginPage.realizaLogin(
                DadosDeTeste.USUARIO_VALIDO,
                DadosDeTeste.SENHA_VALIDA
        );

        // 2. Instancia a nova página de produtos após o redirecionamento
        ProdutosPage produtosPage = new ProdutosPage(driver);

        // 3. Captura o título da tela
        String tituloAtual = produtosPage.obterTituloPagina();


        //  Verificar  se o usuário foi redirecionado para a página de produtos
        Assertions.assertEquals(
                "Products",
                tituloAtual,
                "Falha no login. Usuário não foi redirecionado para a página de produtos."
        );

    }
}
