package br.com.faateodoro.nossacasadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

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

//    @OneToMany(mappedBy = "autor")
//    private Set<Livro> livros;

    @Deprecated
    private Autor(){}

    public Autor(String nome, String email, String descricao) {
        Assert.hasText(nome, "O nome é obrigatório");
        Assert.hasText(descricao, "O email é obrigatório");
        Assert.hasText(descricao, "A descrição é obrigatória");
        Assert.isTrue(descricao.length() <= 400, "A descrição não pode conter mais que 400 caracteres");

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
