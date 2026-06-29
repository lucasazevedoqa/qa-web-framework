package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CarrinhoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By botaoCheckout = By.id("checkout");

    // Localizador do nome do produto dentro do carrinho
    private By nomeProdutoNoCarrinho = By.cssSelector("[data-test='inventory-item-name']");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    // Retorna o nome do produto que está aparecendo no carrinho
    public String obterNomeProdutoNoCarrinho() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nomeProdutoNoCarrinho)).getText();
    }

    public void iniciarCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(botaoCheckout)).click();
    }
}