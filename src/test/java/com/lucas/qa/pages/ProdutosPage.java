package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProdutosPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By botaoAdicionarProduto = By.id("add-to-cart-sauce-labs-backpack");
    private final By badgeCarrinho = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By linkCarrinho = By.className("shopping_cart_link");

    public ProdutosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public void adicionarProdutoAoCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionarProduto)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho));
    }

    public String obterQuantidadeItensCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho)).getText();
    }

    public void acessarCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(linkCarrinho)).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }
}