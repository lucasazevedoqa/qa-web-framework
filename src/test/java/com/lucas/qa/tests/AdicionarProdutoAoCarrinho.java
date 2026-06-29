package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;
import com.lucas.qa.pages.CarrinhoPage; // Importa a nova página
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdicionarProdutoAoCarrinho extends BaseTest {

    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizaLogin(DadosDeTeste.USUARIO_VALIDO, DadosDeTeste.SENHA_VALIDA);

        ProdutosPage produtosPage = new ProdutosPage(driver);
        produtosPage.adiconarMochilaAoCarrinho();

        // 1. Nova ação: Clica no ícone do carrinho
        produtosPage.acessarCarrinho();

        // 2. Instancia a nova página do Carrinho
        CarrinhoPage carrinhoPage = new CarrinhoPage(driver);

        // 3. Pega o nome do produto que está lá dentro
        String nomeProduto = carrinhoPage.obterNomeProdutoNoCarrinho();

        // 4. Valida se o produto é realmente a mochila que adicionamos
        Assertions.assertEquals(
                "Sauce Labs Backpack",
                nomeProduto,
                "O produto no carrinho não é o esperado!"
        );
    }
}