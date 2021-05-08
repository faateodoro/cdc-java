package br.com.faateodoro.nossacasadocodigo.controller.dto;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    @NotBlank(message = "{autor.nome.obrigatorio}")
    private String nome;

    @Email(message = "{autor.email.valido}")
    @NotBlank(message = "{autor.email.valido}")
    private String email;

    @Size(max = 400, message = "{autor.descricao.maximo}")
    @NotBlank(message = "{autor.descricao.obrigatorio}")
    private String descricao;

    private AutorRequest(String nome, String email, String descricao){
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
