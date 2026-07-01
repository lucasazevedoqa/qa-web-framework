package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutInformationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By campoNome = By.id("first-name");
    private final By campoSobrenome = By.id("last-name");
    private final By campoCep = By.id("postal-code");
    private final By botaoContinuar = By.cssSelector("[data-test='continue']");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Preenche o formulário de dados do cliente e prossegue no fluxo de checkout
     * utilizando uma chamada nativa de JavaScript para acionar o botão de avanço.
     * * @param nome Nome do comprador
     * @param sobrenome Sobrenome do comprador
     * @param cep Código postal/CEP do endereço de entrega
     */
    public void preencherDadosEContinuar(String nome, String sobrenome, String cep) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoNome)).sendKeys(nome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoSobrenome)).sendKeys(sobrenome);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoCep)).sendKeys(cep);

        WebElement botao = wait.until(ExpectedConditions.elementToBeClickable(botaoContinuar));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", botao);
    }
}