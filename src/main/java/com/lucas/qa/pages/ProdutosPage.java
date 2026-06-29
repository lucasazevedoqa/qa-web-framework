package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class ProdutosPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizador do título da página de produtos (ex: "Products") para garantir que chegamos nela
    private By barraTituloProdutos = By.cssSelector("[data-test='title']");
    private By botaoAdiconarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private By badgeCarrinho = By.cssSelector("[data-test='shopping-cart-badge']");

    public ProdutosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }
    // Método que verifica se o título da página está visível e retorna o texto dele
    public String obterTituloPagina() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(barraTituloProdutos)).getText();
    }

    public void adiconarMochilaAoCarrinho(){
        wait.until(ExpectedConditions.elementToBeClickable(botaoAdiconarMochila)).click();

    }

    public String obterQuantidadeCarrinho(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(badgeCarrinho)).getText();
    }

    public void acessarCarrinho(){
        wait.until(ExpectedConditions.elementToBeClickable(badgeCarrinho)).click();
    }
}