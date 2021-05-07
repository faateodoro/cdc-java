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
    
    @NotBlank(message = "{autor.nome.obrigatorio}")
    private String nome;

    @Email(message = "{autor.email.valido}")
    @NotBlank(message = "{autor.email.valido}")
    private String email;

    @Size(max = 400, message = "{autor.descricao.maximo}")
    @NotBlank(message = "{autor.descricao.obrigatorio}")
    private String descricao;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    private Autor(){}

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
