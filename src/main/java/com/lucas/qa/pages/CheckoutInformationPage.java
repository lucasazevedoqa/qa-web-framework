package com.lucas.qa.pages;

import com.lucas.qa.config.DadosDeTeste;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutInformationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By botaoContinue = By.id("continue");

    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public void preencherDadosEContinuar(String nome, String sobrenome, String cep){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(nome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(sobrenome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(cep);
        wait.until(ExpectedConditions.visibilityOfElementLocated(botaoContinue)).click();
    }
}
