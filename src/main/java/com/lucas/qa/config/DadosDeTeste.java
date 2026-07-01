package com.lucas.qa.config;

public class DadosDeTeste {

    // MASSA DE DADOS PARA CENÁRIOS DE AUTENTICAÇÃO
    public static final String USUARIO_VALIDO = "standard_user";
    public static final String SENHA_VALIDA = "secret_sauce";
    public static final String SENHA_INVALIDA = "secret_ERRO";

    // MENSAGENS DE VALIDAÇÃO DO SISTEMA
    public static final String MSG_ERRO_LOGIN_INVALIDO = "Epic sadface: Username and password do not match any user in this service";

    /**
     * Construtor privado para impedir a instanciação da classe.
     * Garante que a classe seja utilizada exclusivamente para acesso estático à massa de dados.
     */
    private DadosDeTeste() {
        throw new UnsupportedOperationException("Classe utilitária de dados de teste não deve ser instanciada.");
    }
}