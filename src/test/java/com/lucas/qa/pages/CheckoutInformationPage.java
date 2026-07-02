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
     * @param nome Nome do comprador
     * @param sobrenome Sobrenome do comprador
     * @param cep Código postal/CEP do endereço de entrega
     */
    public void preencherDadosEContinuar(String nome, String sobrenome, String cep) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Garante que os elementos existem na estrutura da página
        WebElement inputNome = wait.until(ExpectedConditions.presenceOfElementLocated(campoNome));
        WebElement inputSobrenome = wait.until(ExpectedConditions.presenceOfElementLocated(campoSobrenome));
        WebElement inputCep = wait.until(ExpectedConditions.presenceOfElementLocated(campoCep));
        WebElement botao = wait.until(ExpectedConditions.presenceOfElementLocated(botaoContinuar));

        // 2. Centraliza o formulário na tela
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botao);

        // 3. Preenche os dados injetando diretamente o valor via JavaScript (Inquebrável no CI/CD)
        js.executeScript("arguments[0].value = arguments[1];", inputNome, nome);
        js.executeScript("arguments[0].value = arguments[1];", inputSobrenome, sobrenome);
        js.executeScript("arguments[0].value = arguments[1];", inputCep, cep);

        // 4. Efetua o clique clássico do Selenium (com Wait de clique)
        wait.until(ExpectedConditions.elementToBeClickable(botao)).click();

        // 5. Aguarda a transição de página para o step-two
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}