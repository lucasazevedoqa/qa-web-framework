package com.lucas.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lucas.qa.config.Config;

public class CheckoutInformationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By campoNome = By.id("first-name");
    private final By campoSobrenome = By.id("last-name");
    private final By campoCep = By.id("postal-code");
    private final By botaoContinuar = By.id("continue");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Preenche os dados do cliente e prossegue para a próxima etapa do checkout.
     */
    public void preencherDadosEContinuar(String nome, String sobrenome, String cep) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement inputNome = wait.until(
                ExpectedConditions.visibilityOfElementLocated(campoNome));

        WebElement inputSobrenome = wait.until(
                ExpectedConditions.visibilityOfElementLocated(campoSobrenome));

        WebElement inputCep = wait.until(
                ExpectedConditions.visibilityOfElementLocated(campoCep));

        WebElement botao = wait.until(
                ExpectedConditions.visibilityOfElementLocated(botaoContinuar));

        // Centraliza o botão na tela
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", botao);

        // Nome
        inputNome.clear();
        inputNome.sendKeys(nome);

        // Sobrenome
        inputSobrenome.clear();
        inputSobrenome.sendKeys(sobrenome);

        // CEP
        inputCep.clear();
        inputCep.sendKeys(cep);

        // Aguarda o valor realmente ser preenchido
        wait.until(driver ->
                cep.equals(inputCep.getAttribute("value")));

        // Aguarda botão habilitado e clicável
        wait.until(ExpectedConditions.elementToBeClickable(botao));

        // Clica normalmente
        botao.click();

        // Caso o clique não funcione (comum em CI/CD), tenta via JavaScript
        if (!driver.getCurrentUrl().contains("checkout-step-two.html")) {

            wait.until(ExpectedConditions.elementToBeClickable(botao));

            js.executeScript("arguments[0].click();", botao);
        }

        // Aguarda a mudança de página
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}