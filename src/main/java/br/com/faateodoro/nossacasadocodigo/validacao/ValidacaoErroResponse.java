package br.com.faateodoro.nossacasadocodigo.validacao;

public class ValidacaoErroResponse {
    private String campo;
    private String mensagem;

    public ValidacaoErroResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
