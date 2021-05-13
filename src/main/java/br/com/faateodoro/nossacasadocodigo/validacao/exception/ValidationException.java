package br.com.faateodoro.nossacasadocodigo.validacao.exception;

public class ValidationException extends RuntimeException{

    private String campo;

    public String getCampo() {
        return campo;
    }

    public ValidationException(String message, String campo) {
        super(message);
        this.campo = campo;
    }
}