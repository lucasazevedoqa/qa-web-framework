package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; // 1. Importação necessária
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By botaoContinue = By.cssSelector("[data-test='continue']");

    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    public void preencherDadosEContinuar(String nome, String sobrenome, String cep){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(nome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(sobrenome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(cep);

        // 2. Espera o botão estar pronto para receber a ação
        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(botaoContinue));

        // 3. Força o clique via JavaScript (Ignora qualquer barreira física ou foco do driver)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", botao);
    }
}