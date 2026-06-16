package com.lucas.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    // 1. Variável para guardar o driver (navegador)
    private WebDriver driver;

    // 2. Definindo os elementos da página (Localizadores)
    // Usamos 'By' para encontrar elementos na tela
    private By userField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // Mensagem de erro exibida quando login falaha
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Retorna o texto da mensagem de erro exibida na tela
    public String obterMensagemErro(){
        return driver.findElement(errorMessage).getText();
    }

    // 3. Construtor: Recebe o driver que está vindo do seu teste
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // 4. Ação: O que  o usuário faz nesta página
    public void realizaLogin(String usuario, String senha){
        driver.findElement(userField).sendKeys(usuario);
        driver.findElement(passwordField).sendKeys(senha);
        driver.findElement(loginButton).click();
    }

}