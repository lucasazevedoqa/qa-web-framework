package com.lucas.qa.pages;

import com.lucas.qa.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object responsável pela tela de login do SauceDemo.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizadores
    private final By campoUsuario = By.id("user-name");
    private final By campoSenha = By.id("password");
    private final By botaoLogin = By.id("login-button");
    private final By mensagemErro = By.className("error-message-container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(Config.TIMEOUT_PADRAO)
        );
    }

    /**
     * Realiza login na aplicação.
     *
     * @param usuario usuário de acesso
     * @param senha senha de acesso
     */
    public void realizarLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarBotaoLogin();
    }

    /**
     * Retorna a mensagem de erro exibida após uma tentativa inválida de login.
     *
     * @return texto da mensagem de erro
     */
    public String obterMensagemDeErro() {
        return obterTexto(mensagemErro);
    }

    private void preencherUsuario(String usuario) {
        preencherCampo(campoUsuario, usuario);
    }

    private void preencherSenha(String senha) {
        preencherCampo(campoSenha, senha);
    }

    private void clicarBotaoLogin() {
        clicar(botaoLogin);
    }

    private void preencherCampo(By localizador, String texto) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(localizador))
                .clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(localizador))
                .sendKeys(texto);
    }

    private void clicar(By localizador) {
        wait.until(ExpectedConditions.elementToBeClickable(localizador))
                .click();
    }

    private String obterTexto(By localizador) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador))
                .getText();
    }
}