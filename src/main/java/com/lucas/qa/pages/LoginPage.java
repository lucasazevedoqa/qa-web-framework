package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    // 1. Variáveis da classe
    private WebDriver driver;
    private WebDriverWait wait;

    // 2. Localizadores (Elementos da página)
    private By userField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // 3. Construtor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    // 4. Ações da página
    public void realizaLogin(String usuario, String senha){
        // Corrigido para usar 'userField' e 'passwordField' que você declarou acima
        wait.until(ExpectedConditions.visibilityOfElementLocated(userField)).sendKeys(usuario);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(senha);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String obterMensagemErro(){
        // Adicionado o wait aqui também para garantir que o erro apareceu antes de pegar o texto
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}