package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

        preencherCampoComFallback(campoNome, nome, "Nome");
        preencherCampoComFallback(campoSobrenome, sobrenome, "Sobrenome");
        preencherCampoComFallback(campoCep, cep, "CEP");

        clicarContinuar();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout-step-two.html"),
                ExpectedConditions.visibilityOfElementLocated(mensagemErro)
        ));

        if (driver.getCurrentUrl().contains("checkout-step-one.html")
                && !driver.findElements(mensagemErro).isEmpty()) {

            String erro = driver.findElement(mensagemErro).getText();

            throw new RuntimeException(
                    "Checkout não avançou. Erro exibido na tela: " + erro
            );
        }
    }

    private void preencherCampoComFallback(By localizador, String valor, String nomeCampo) {
        WebElement campo = wait.until(ExpectedConditions.elementToBeClickable(localizador));

        limparEPreencherComSelenium(campo, valor);

        if (!campoFoiPreenchido(localizador, valor)) {
            preencherComActions(localizador, valor);
        }

        if (!campoFoiPreenchido(localizador, valor)) {
            preencherComJavascriptNativo(localizador, valor);
        }

        if (!campoFoiPreenchido(localizador, valor)) {
            String valorAtual = driver.findElement(localizador).getAttribute("value");

            throw new RuntimeException(
                    nomeCampo + " não foi preenchido corretamente. Esperado: '"
                            + valor + "'. Atual: '" + valorAtual + "'."
            );
        }
    }

    private void limparEPreencherComSelenium(WebElement campo, String valor) {
        campo.click();
        campo.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        campo.sendKeys(Keys.BACK_SPACE);
        campo.sendKeys(valor);
    }

    private void preencherComActions(By localizador, String valor) {
        WebElement campo = wait.until(ExpectedConditions.elementToBeClickable(localizador));

        new Actions(driver)
                .moveToElement(campo)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(valor)
                .perform();
    }

    private void preencherComJavascriptNativo(By localizador, String valor) {
        WebElement campo = wait.until(ExpectedConditions.presenceOfElementLocated(localizador));

        ((JavascriptExecutor) driver).executeScript(
                "const input = arguments[0];" +
                        "const value = arguments[1];" +
                        "input.focus();" +
                        "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "setter.call(input, value);" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "input.blur();",
                campo,
                valor
        );
    }

    private boolean campoFoiPreenchido(By localizador, String valorEsperado) {
        String valorAtual = driver.findElement(localizador).getAttribute("value");
        return valorEsperado.equals(valorAtual);
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