package com.lucas.qa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Construtor privado para impedir a instanciação da classe.
     * Garante que os recursos de captura de imagem sejam acessados estritamente via método estático.
     */
    private ScreenshotUtils() {
        throw new UnsupportedOperationException("Classe utilitária de screenshots não deve ser instanciada.");
    }

    /**
     * Captura uma imagem da tela atual do navegador e a armazena no diretório de evidências local.
     * O nome do arquivo é gerado dinamicamente com base no status, nome do cenário e data/hora.
     * @param driver Instância ativa do WebDriver que controla o navegador
     * @param nomeTeste Nome identificador do cenário de teste executado
     * @param status Resultado do cenário (ex: "SUCESSO" ou "FALHA")
     */
    public static void tirarScreenshot(WebDriver driver, String nomeTeste, String status) {
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String nomeArquivo = String.format("%s_%s_%s.png", status, nomeTeste, timestamp);

        File destino = new File("evidencias/" + nomeArquivo);

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File arquivoOriginal = screenshot.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(arquivoOriginal, destino);
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
        }
    }
}