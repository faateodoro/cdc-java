package br.com.faateodoro.nossacasadocodigo.controller.dto;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;

public class AutorForm {
    private String nome;
    private String email;
    private String descricao;

    private AutorForm(String nome, String email, String descricao){
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor toAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
