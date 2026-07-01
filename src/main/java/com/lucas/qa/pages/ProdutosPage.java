package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class ProdutosPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // LOCALIZADORES (Centralizados e imutáveis)
    private final By barraTituloProdutos = By.cssSelector("[data-test='title']");
    private final By botaoAdicionarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private final By badgeCarrinho = By.cssSelector("[data-test='shopping-cart-badge']");

    public ProdutosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Captura o título principal da página para validação de fluxo.
     * @return String contendo o texto (ex: "Products")
     */
    public String obterTituloPagina() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(barraTituloProdutos)).getText();
    }

    /**
     * Adiciona especificamente a mochila ao carrinho de compras.
     */
    public void adicionarMochilaAoCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionarMochila)).click();
    }

    /**
     * MÉTODO BONUS (Escalabilidade): Permite adicionar qualquer produto dinamicamente pelo nome formatado.
     * Exemplo de uso: adicionarProdutoAoCarrinho("bike-light");
     */
    public void adicionarProdutoAoCarrinho(String nomeProdutoSlug) {
        By seletorDinamico = By.id("add-to-cart-sauce-labs-" + nomeProdutoSlug);
        wait.until(ExpectedConditions.elementToBeClickable(seletorDinamico)).click();
    }

    /**
     * Recupera a quantidade de itens exibida no ícone do carrinho.
     * @return String com o número de itens (ex: "1")
     */
    public String obterQuantidadeCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho)).getText();
    }

    /**
     * Clica no ícone do carrinho para direcionar o usuário à página de listagem de compras.
     */
    public void acessarCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(badgeCarrinho)).click();
    }
}