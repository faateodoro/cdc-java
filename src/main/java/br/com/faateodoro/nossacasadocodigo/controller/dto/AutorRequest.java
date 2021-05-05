package br.com.faateodoro.nossacasadocodigo.controller.dto;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O email é obrigatório e deve ter o formato válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @Size(max = 400, message = "Adescrição nãopode conter mais que 400 caracteres")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    private AutorRequest(String nome, String email, String descricao){
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
