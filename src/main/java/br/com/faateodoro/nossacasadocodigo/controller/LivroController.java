package br.com.faateodoro.nossacasadocodigo.controller;

import br.com.faateodoro.nossacasadocodigo.controller.dto.LivroDto;
import br.com.faateodoro.nossacasadocodigo.modelo.Livro;
import br.com.faateodoro.nossacasadocodigo.repository.AutorRepository;
import br.com.faateodoro.nossacasadocodigo.repository.CategoriaRepository;
import br.com.faateodoro.nossacasadocodigo.repository.LivroRepository;
import br.com.faateodoro.nossacasadocodigo.validacao.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LivroController {

    private LivroRepository livroRepository;
    private CategoriaRepository categoriaRepository;
    private AutorRepository autorRepository;

    public LivroController(LivroRepository livroRepository,
                           CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
        this.autorRepository = autorRepository;
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> cadastrar(@RequestBody @Valid LivroDto livroDto)
            throws MethodArgumentNotValidException{

        Optional<Livro> tituloJaExiste = livroRepository.findByTitulo(livroDto.getTitulo());
        Optional<Livro> isbnJaExiste = livroRepository.findByIsbn(livroDto.getIsbn());

        if(tituloJaExiste.isPresent())
            throw new ValidationException("Operação ilegal. O título já existe", "titulo");

        if(isbnJaExiste.isPresent())
            throw new ValidationException("Operação ilegal. O ISBN já existe", "isbn");

        Livro livro = livroDto.ToLivro(categoriaRepository, autorRepository);
        livroRepository.save(livro);
        return ResponseEntity.ok(livro);
    }
}
