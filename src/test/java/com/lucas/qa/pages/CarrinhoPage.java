package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; // Importado para estabilidade no CI
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // Importado para manipular o elemento
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
     * Otimizado com scroll e JS-click para máxima estabilidade em pipelines de CI/CD.
     */
    public void iniciarCheckout() {
        // 1. Aguarda o elemento estar pronto na árvore do HTML
        WebElement botao = wait.until(ExpectedConditions.presenceOfElementLocated(botaoCheckout));

        // 2. Rola a tela até o botão (Modo Headless precisa disso para validar a visibilidade)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", botao);

        // 3. Aguarda ele ficar clicável e efetua o clique robusto via JS para evitar interrupções de renderização
        wait.until(ExpectedConditions.elementToBeClickable(botao));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botao);
    }
}