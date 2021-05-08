package br.com.faateodoro.nossacasadocodigo.validacao.exception;

public class EmailJaExistenteException extends RuntimeException{

    public EmailJaExistenteException(String message) {
        super(message);
    }
}