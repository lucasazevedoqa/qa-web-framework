package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CarrinhoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By botaoCheckout = By.id("checkout");
    private final By nomeProdutoNoCarrinho = By.cssSelector("[data-test='inventory-item-name']");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Captura o texto do nome do produto presente na listagem do carrinho.
     * @return String contendo o nome do item visível no carrinho
     */
    public String obterNomeProdutoNoCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nomeProdutoNoCarrinho)).getText();
    }

    /**
     * Executa o clique no botão de checkout para prosseguir para a etapa de faturamento.
     */
    public void iniciarCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(botaoCheckout)).click();
    }
}