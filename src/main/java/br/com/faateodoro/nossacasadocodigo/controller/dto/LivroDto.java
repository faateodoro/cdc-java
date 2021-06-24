package br.com.faateodoro.nossacasadocodigo.controller.dto;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import br.com.faateodoro.nossacasadocodigo.modelo.Categoria;
import br.com.faateodoro.nossacasadocodigo.modelo.Livro;
import br.com.faateodoro.nossacasadocodigo.repository.AutorRepository;
import br.com.faateodoro.nossacasadocodigo.repository.CategoriaRepository;
import br.com.faateodoro.nossacasadocodigo.validacao.exception.ValidationException;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class LivroDto {
    @NotBlank(message = "{livro.titulo.obrigatorio}")
    private String titulo;

    @Length(max=500, message = "{livro.resumo.maximo}")
    @NotBlank(message = "{livro.resumo.obrigatorio}")
    private String resumo;

    @NotBlank(message = "{livro.sumario.obrigatorio}")
    private String sumario;

    @DecimalMin(value="20.00", message = "{livro.preco.minimo}")
    private BigDecimal preco;

    @Min(value=100, message = "{livro.paginas.minimo}")
    private Integer paginas;

    @NotBlank(message = "{livro.isbn.obrigatorio}")
    private String isbn;

    @Future(message = "{livro.publicacao.futuro}")
    private LocalDate dataPublicacao;

    @NotBlank(message = "{livro.categoria.obrigatorio}")
    private String nomeCategoria;

    @NotBlank(message = "{libro.autor.obrigatorio}")
    private String emailAutor;

    public LivroDto(String titulo, String resumo, String sumario, BigDecimal preco,
                    Integer paginas, String isbn, LocalDate dataPublicacao, String nomeCategoria, String emailAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.nomeCategoria = nomeCategoria;
        this.emailAutor = emailAutor;
    }

    public Livro toLivro(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {

        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(this.nomeCategoria);
        Optional<Autor> autorOptional = autorRepository.findByEmail(this.emailAutor);

        if(!categoriaOptional.isPresent())
            throw new ValidationException("Operação ilegal. Essa categoria não está cadastrada!", "categoria");

        if(!autorOptional.isPresent())
            throw new ValidationException("Operação ilegal. Este autor não está cadastrado!", "autor");

        Categoria categoria = categoriaOptional.get();
        Autor autor = autorOptional.get();
        Livro livro = new Livro(this.titulo, this.resumo, this.sumario,
            this.preco, this.paginas, this.isbn, this.dataPublicacao, categoria, autor);

        return livro;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }
}
