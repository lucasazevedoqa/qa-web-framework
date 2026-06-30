package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; // Importação necessária
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By labelPrecoItem = By.className("summary_subtotal_label");
    private By botaoFinish = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public String obterValorTotalDoItem() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(labelPrecoItem)).getText();
    }

    public void finalizarCompra() {
        // Aguarda o botão Finish estar pronto
        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(botaoFinish));

        // Força o clique direto via JavaScript no navegador
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", botao);


    }
}