package br.com.faateodoro.nossacasadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{livro.titulo.obrigatorio}")
    private String titulo;

    @Size(max=500, message = "{livro.resumo.maximo}")
    @NotBlank(message = "{livro.resumo.obrigatorio}")
    private String resumo;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "{livro.sumario.obrigatorio}")
    private String sumario;

    @DecimalMin(value="20.0", message = "{livro.preco.minimo}")
    private BigDecimal preco;

    @Min(value=100, message = "{livro.paginas.minimo}")
    private Integer paginas;

    @NotBlank(message = "{livro.isbn.obrigatorio}")
    private String isbn;

    @Future(message = "{livro.publicacao.futuro}")
    private LocalDate dataPublicacao;

    @NotNull(message = "{livro.categoria.obrigatorio}")
    @ManyToOne
    private Categoria categoria;

    @NotNull(message = "{livro.autor.obrigatorio}")
    @ManyToOne
    private Autor autor;

    @Deprecated
    private Livro(){}

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco,
                 Integer paginas, String isbn, LocalDate dataPublicacao,
                 Categoria categoria, Autor autor) {
        Assert.hasText(titulo, "O título é obrigatório");
        Assert.hasText(resumo, "O resumo é obrigatório");
        Assert.hasText(sumario, "O sumário é obrigatório");
        Assert.notNull(preco, "O preço é obrigatório");
        Assert.isTrue(preco.compareTo(new BigDecimal("20.00")) >= 0,
                "O preço deve ser ao menos R$20,00");
        Assert.isTrue(paginas >= 100, "O livro deve conter ao menos 100 páginas");
        Assert.hasText(isbn, "O ISBN é obrigatório");
        Assert.notNull(dataPublicacao, "A data de publicação é obrigatória");
        Assert.isTrue(dataPublicacao.isAfter(LocalDate.now()),
                "A data de publicação deve ser uma data futura");
        Assert.notNull(categoria, "A categoria deve ser cadastrada previamente.");
        Assert.notNull(autor, "O autor deve ser cadastrada previamente.");

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
