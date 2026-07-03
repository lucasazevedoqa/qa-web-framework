package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarrinhoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By tituloCarrinho = By.className("title");
    private final By nomeProdutoNoCarrinho = By.className("inventory_item_name");
    private final By botaoCheckout = By.id("checkout");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public String obterTituloCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloCarrinho)).getText();
    }

    public String obterNomeProdutoNoCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nomeProdutoNoCarrinho)).getText();
    }

    public void iniciarCheckout() {
        wait.until(ExpectedConditions.urlContains("cart.html"));

        WebElement checkout = wait.until(
                ExpectedConditions.presenceOfElementLocated(botaoCheckout)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                checkout
        );

        wait.until(ExpectedConditions.elementToBeClickable(botaoCheckout));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                checkout
        );

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}