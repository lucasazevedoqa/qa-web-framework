package com.lucas.qa.config;

public class Config {

    // CONSTANTES GLOBAIS DE AMBIENTE
    public static final String URL_BASE = "https://www.saucedemo.com/";
    public static final long TIMEOUT_PADRAO = 10;

    /**
     * Construtor privado para impedir a instanciação da classe.
     * Garante que a classe seja utilizada exclusivamente para acesso estático a constantes.
     */
    private Config() {
        throw new UnsupportedOperationException("Classe utilitária de configuração não deve ser instanciada.");
    }
}