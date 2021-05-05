package br.com.faateodoro.nossacasadocodigo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O email é obrigatório e deve ter o formato válido")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @Size(max = 400, message = "Adescrição nãopode conter mais que 400 caracteres")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Autor(){}

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
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
