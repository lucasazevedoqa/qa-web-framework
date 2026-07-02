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
        // 1. Aguarda a estabilização da página
        wait.until(ExpectedConditions.titleContains("Swag Labs"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cancel")));

        // 2. Localiza os elementos
        WebElement inputNome = wait.until(ExpectedConditions.visibilityOfElementLocated(campoNome));
        WebElement inputSobrenome = wait.until(ExpectedConditions.visibilityOfElementLocated(campoSobrenome));
        WebElement inputCep = wait.until(ExpectedConditions.visibilityOfElementLocated(campoCep));
        WebElement botao = wait.until(ExpectedConditions.presenceOfElementLocated(botaoContinuar));

        // 3. Preenche os dados (Lógica idêntica à que já está funcionando)
        inputNome.clear();
        inputNome.sendKeys(nome);

        inputSobrenome.clear();
        inputSobrenome.sendKeys(sobrenome);

        inputCep.clear();
        inputCep.sendKeys(cep);

        // 4. O XEQUE-MATE: Força o clique via JavaScript direto no elemento do botão.
        // Isso resolve o problema de o botão ser ignorado no modo headless da nuvem.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botao);

        // 5. Aguarda a transição de página simples
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}