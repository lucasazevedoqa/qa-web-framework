package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdicionarProdutoAoCarrinho extends BaseTest {

    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso(){

        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizaLogin(
                DadosDeTeste.USUARIO_VALIDO,
                DadosDeTeste.SENHA_VALIDA
        );

        ProdutosPage produtosPage = new ProdutosPage(driver);

        produtosPage.adiconarMochilaAoCarrinho();


        String quantidadeNoCarrinho = produtosPage.obterQuantidadeCarrinho();

        Assertions.assertEquals(
                "1",
                quantidadeNoCarrinho,
                "O produto não foi adicionado corretamente ao carrinho."
        );
    }

}
