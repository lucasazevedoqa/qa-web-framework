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

    public static void tirarScreenshot(WebDriver driver, String nomeTeste, String status) {
        // Cria o formato de data para o nome do arquivo não sobrescrever o anterior
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String nomeArquivo = String.format("%s_%s_%s.png", status, nomeTeste, timestamp);

        // Define o caminho da pasta raiz do projeto (/evidencias)
        File destino = new File("evidencias/" + nomeArquivo);

        try {
            // Coage o driver para a interface de Screenshot do Selenium
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File arquivoOriginal = screenshot.getScreenshotAs(OutputType.FILE);

            // Copia o arquivo temporário para a pasta definitiva
            FileUtils.copyFile(arquivoOriginal, destino);
            System.out.println("📸 Evidência salva em: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
        }
    }
}