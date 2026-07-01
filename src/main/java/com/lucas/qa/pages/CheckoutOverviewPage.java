package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By labelPrecoItem = By.className("summary_subtotal_label");
    private final By botaoFinish = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Recupera o texto contendo o subtotal dos itens listados no resumo do pedido.
     * @return String contendo o rótulo de preço resumido (ex: "Item total: $29.99")
     */
    public String obterValorTotalDoItem() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelPrecoItem)).getText();
    }

    /**
     * Finaliza o processo de compra acionando o botão de conclusão
     * através de uma execução nativa de script em JavaScript.
     */
    public void finalizarCompra() {
        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(botaoFinish));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", botao);
    }
}