package br.com.faateodoro.nossacasadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{categoria.nome.obrigatorio}")
    private String nome;

//    @OneToMany(mappedBy = "categoria")
//    private Set<Livro> livros;

    @Deprecated
    private Categoria(){}

    public Categoria(String nome){
        Assert.hasText(nome, "O nome é obrigatório");

        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
