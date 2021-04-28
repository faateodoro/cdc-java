package br.com.faateodoro.nossacasadocodigo.modelo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Entity
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "O nome não pode ser nulo")
    //@NotEmpty(message = "O nome não pode ser vazio")
    //@Length(min = 1)
    private String nome;

    //@NotNull(message = "O email não pode ser nulo")
    //@NotEmpty(message = "O email não pode ser vazio")
    //@Length(min = 1)
    private String email;

    //@NotNull(message = "A descrição não pode ser nula")
    //@NotEmpty(message = "A descrição não pode ser vazia")
    //@Length(min = 1, max = 400)
    private String descricao;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Autor(){}

    public Autor(String nome, String email, String descricao) {
        if(nomeEhVazio(nome))
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        if(emailEhVazio(email))
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        if(!validaEmail(email))
            throw new IllegalArgumentException("Email está em um formato inválido.");
        if(descricaoEhVazia(descricao))
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        if(validaDescricao(descricao))
            throw new IllegalArgumentException("Descrição não pode ter mais que 400 caracteres");

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    private boolean validaDescricao(String descricao) {
        return descricao.length() > 400;
    }

    private boolean validaEmail(String email) {
        return Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .find();
    }

    private boolean descricaoEhVazia(String descricao) {
        return descricao == null || descricao.isBlank();
    }

    private boolean emailEhVazio(String email) {
        return email == null || email.isBlank();
    }

    private boolean nomeEhVazio(String nome){
        return nome == null || nome.isBlank();
    }

    public Long getId(){
        return this.id;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
