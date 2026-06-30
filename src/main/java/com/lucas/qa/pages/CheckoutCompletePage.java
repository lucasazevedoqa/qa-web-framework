package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutCompletePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // CORREÇÃO: Trocando o cssSelector instável pela classe nativa do título de sucesso
    private By mensagemSucessoHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public String obterMensagemDeSucesso() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemSucessoHeader)).getText();
    }
}
