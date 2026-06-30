package com.lucas.qa.tests;

import com.lucas.qa.base.BaseTest;
import com.lucas.qa.config.DadosDeTeste;
import com.lucas.qa.pages.LoginPage;
import com.lucas.qa.pages.ProdutosPage;
import com.lucas.qa.pages.CarrinhoPage;
import com.lucas.qa.pages.CheckoutInformationPage;
import com.lucas.qa.pages.CheckoutOverviewPage;
import com.lucas.qa.pages.CheckoutCompletePage; // 1. Importa a nova página final
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdicionarProdutoAoCarrinho extends BaseTest {

    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizaLogin(DadosDeTeste.USUARIO_VALIDO, DadosDeTeste.SENHA_VALIDA);

        ProdutosPage produtosPage = new ProdutosPage(driver);
        produtosPage.adiconarMochilaAoCarrinho();
        produtosPage.acessarCarrinho();

        CarrinhoPage carrinhoPage = new CarrinhoPage(driver);
        String nomeProduto = carrinhoPage.obterNomeProdutoNoCarrinho();
        Assertions.assertEquals("Sauce Labs Backpack", nomeProduto, "O produto no carrinho não é o esperado!");
        carrinhoPage.iniciarCheckout();

        CheckoutInformationPage checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutInfoPage.preencherDadosEContinuar("Lucas", "Freitas", "38400-000");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        String precoTexto = overviewPage.obterValorTotalDoItem();
        Assertions.assertTrue(precoTexto.contains("29.99"), "O valor total do item exibido na revisão está incorreto!");
        overviewPage.finalizarCompra();

        // 2. Instancia a tela de sucesso final
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

        // 3. Captura o texto de agradecimento e valida a finalização real
        String mensagemFinal = completePage.obterMensagemDeSucesso();
        Assertions.assertEquals(
                "Thank you for your order!",
                mensagemFinal,
                "A mensagem de finalização de compra está incorreta!"
        );
    }
}