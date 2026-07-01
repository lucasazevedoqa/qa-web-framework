package com.lucas.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lucas.qa.config.Config;
import java.time.Duration;

public class CheckoutCompletePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By mensagemSucessoHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT_PADRAO));
    }

    /**
     * Captura o texto do cabeçalho de confirmação exibido após a conclusão do pedido.
     * @return String contendo a mensagem de sucesso (ex: "Thank you for your order!")
     */
    public String obterMensagemDeSucesso() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemSucessoHeader)).getText();
    }
}