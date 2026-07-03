package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;
import com.lucas.qa.pages.CarrinhoPage;
import com.lucas.qa.pages.CheckoutInformationPage;
import com.lucas.qa.pages.CheckoutOverviewPage;
import com.lucas.qa.pages.CheckoutCompletePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe responsável por agrupar os cenários de testes funcionais
 * relacionados à inclusão de itens no carrinho e fluxo de compra.
 */
public class AdicionarProdutoAoCarrinhoTest extends BaseTest {

    /**
     * Valida o fluxo de ponta a ponta da compra de um produto,
     * desde a autenticação até a validação da tela final de sucesso.
     */
    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso() {
        LoginPage loginPage = new LoginPage(driver);
        ProdutosPage produtosPage = new ProdutosPage(driver);

        loginPage.realizarLogin(DadosDeTeste.USUARIO_VALIDO, DadosDeTeste.SENHA_VALIDA);

        produtosPage.adicionarMochilaAoCarrinho();

        Assertions.assertEquals(
                "1",
                produtosPage.obterQuantidadeItensCarrinho(),
                "A quantidade de itens no carrinho está incorreta!"
        );

        produtosPage.acessarCarrinho();

        CarrinhoPage carrinhoPage = new CarrinhoPage(driver);

        Assertions.assertEquals(
                "Your Cart",
                carrinhoPage.obterTituloCarrinho(),
                "O usuário não foi redirecionado para a página do carrinho!"
        );

        String nomeProduto = carrinhoPage.obterNomeProdutoNoCarrinho();

        Assertions.assertEquals(
                "Sauce Labs Backpack",
                nomeProduto,
                "O produto no carrinho não é o esperado!"
        );

        carrinhoPage.iniciarCheckout();

        CheckoutInformationPage checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutInfoPage.preencherDadosEContinuar("Lucas", "Freitas", "38400000");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        String precoTexto = overviewPage.obterValorTotalDoItem();

        Assertions.assertTrue(
                precoTexto.contains("29.99"),
                "O valor total do item exibido na revisão está incorreto!"
        );

        overviewPage.finalizarCompra();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String mensagemFinal = completePage.obterMensagemDeSucesso();

        Assertions.assertEquals(
                "Thank you for your order!",
                mensagemFinal,
                "A mensagem de finalização de compra está incorreta!"
        );
    }
}