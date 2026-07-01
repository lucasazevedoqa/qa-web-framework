package com.lucas.qa.utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilitário responsável pela captura de evidências visuais (screenshots)
 * e integração com os relatórios do Allure Report.
 */
public class ScreenshotUtils {

    /**
     * Captura um screenshot da tela atual do navegador, salva o arquivo físico
     * na pasta local /evidencias e anexa a imagem automaticamente ao relatório do Allure.
     *
     * @param driver     Instância ativa do WebDriver.
     * @param nomeTeste  Nome do cenário de teste para composição do arquivo.
     * @param status     Resultado do teste (PASSED/FAILED) para catalogação.
     */
    public static void tirarScreenshot(WebDriver driver, String nomeTeste, String status) {
        if (driver == null) {
            System.err.println("[ERRO] WebDriver nulo. Não foi possível capturar o screenshot.");
            return;
        }

        try {
            // 1. Captura os bytes do screenshot para o Allure
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            anexarAoAllure(nomeTeste, screenshotBytes);

            // 2. Mantém o salvamento físico do arquivo na sua pasta local para auditoria rápida
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String nomeArquivo = String.format("evidencias/%s_%s_%s.png", status, nomeTeste, timestamp);

            FileUtils.copyFile(srcFile, new File(nomeArquivo));
            System.out.println("[INFO] Evidência física salva em: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("[ERRO] Falha ao manipular o arquivo de screenshot: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERRO] Erro inesperado ao tirar screenshot: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar anotado com @Attachment.
     * O Allure intercepta o array de bytes retornado aqui e insere o print no dashboard.
     */
    @Attachment(value = "Evidência - {nomeTeste}", type = "image/png")
    private static byte[] anexarAoAllure(String nomeTeste, byte[] evidência) {
        return evidência;
    }
}