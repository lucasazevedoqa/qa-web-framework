package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInformationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By campoNome = By.id("first-name");
    private final By campoSobrenome = By.id("last-name");
    private final By campoCep = By.id("postal-code");
    private final By botaoContinue = By.id("continue");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Config.TIMEOUT_PADRAO)
        );
    }

    public void preencherDadosEContinuar(String nome, String sobrenome, String cep) {
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(campoNome)).sendKeys(nome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoSobrenome)).sendKeys(sobrenome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoCep)).sendKeys(cep);

        wait.until(ExpectedConditions.elementToBeClickable(botaoContinue)).click();

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}