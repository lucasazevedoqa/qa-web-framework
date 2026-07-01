package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // LOCALIZADORES (IDs nativos e estáveis)
    private final By campoUsuario = By.id("user-name");
    private final By campoSenha = By.id("password");
    private final By botaoLogin = By.id("login-button");

    // CORREÇÃO: Atualizado para a classe CSS estável do contêiner de erro do SauceDemo
    private final By mensagemErro = By.className("error-message-container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Realiza o fluxo completo de autenticação na plataforma.
     * @param usuario Nome de usuário para acesso
     * @param senha Senha correspondente
     */
    public void realizarLogin(String usuario, String senha) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoUsuario)).sendKeys(usuario);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoSenha)).sendKeys(senha);
        wait.until(ExpectedConditions.elementToBeClickable(botaoLogin)).click();
    }

    /**
     * Captura o texto do alerta de erro caso as credenciais sejam inválidas.
     * @return String contendo o texto visível da mensagem de erro
     */
    public String obterMensagemDeErro() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemErro)).getText();
    }
}