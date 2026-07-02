package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private final By mensagemErro = By.cssSelector("[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Config.TIMEOUT_PADRAO)
        );
    }

    public void preencherDadosEContinuar(String nome, String sobrenome, String cep) {
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        preencherCampo(campoNome, nome);
        preencherCampo(campoSobrenome, sobrenome);
        preencherCampo(campoCep, cep);

        validarValorCampo(campoNome, nome);
        validarValorCampo(campoSobrenome, sobrenome);
        validarValorCampo(campoCep, cep);

        clicarContinuar();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout-step-two.html"),
                ExpectedConditions.visibilityOfElementLocated(mensagemErro)
        ));

        if (driver.getCurrentUrl().contains("checkout-step-one.html")
                && !driver.findElements(mensagemErro).isEmpty()) {
            String erro = driver.findElement(mensagemErro).getText();
            throw new RuntimeException("Checkout não avançou. Erro exibido na tela: " + erro);
        }
    }

    private void preencherCampo(By localizador, String valor) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));

        campo.clear();
        campo.sendKeys(valor);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                campo,
                valor
        );
    }

    private void validarValorCampo(By localizador, String valorEsperado) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));

        wait.until(driver -> valorEsperado.equals(campo.getAttribute("value")));
    }

    private void clicarContinuar() {
        WebElement continuar = wait.until(ExpectedConditions.presenceOfElementLocated(botaoContinue));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                continuar
        );

        wait.until(ExpectedConditions.elementToBeClickable(botaoContinue));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                continuar
        );
    }
}