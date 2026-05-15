package br.com.streaming.util;

public class Validador {

    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean validarDuracao(int duracao) {
        return duracao > 0 && duracao < 3600;
    }

    public static boolean validarIndice(int index, int tamanho) {
        return index >= 0 && index < tamanho;
    }
}
