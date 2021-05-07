package br.com.faateodoro.nossacasadocodigo.controller.dto;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

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
        Assert.hasText(nome, "O nome é obrigatório");
        Assert.isTrue(validaEmail(email), "O email é obrigatório e deve ter o formato válido");
        Assert.hasText(descricao, "A descrição é obrigatória");
        Assert.isTrue(descricao.length() <= 400, "A descrição não pode conter mais que 400 caracteres");

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    private boolean validaEmail(String email) {
        return Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE)
            .matcher(email)
            .find();
    }

    public Autor toAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
