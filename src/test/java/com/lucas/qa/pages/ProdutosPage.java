package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProdutosPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By tituloPaginaProdutos = By.className("title");
    private final By botaoAdicionarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private final By badgeCarrinho = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By linkCarrinho = By.cssSelector("a.shopping_cart_link");

    public ProdutosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public String obterTituloPagina() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloPaginaProdutos)).getText();
    }

    public void adicionarMochilaAoCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(botaoAdicionarMochila)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho));
    }

    public String obterQuantidadeItensCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho)).getText();
    }

    public void acessarCarrinho() {
        WebElement carrinho = wait.until(ExpectedConditions.presenceOfElementLocated(linkCarrinho));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                carrinho
        );

        wait.until(ExpectedConditions.elementToBeClickable(linkCarrinho));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                carrinho
        );

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }
}