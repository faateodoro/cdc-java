package br.com.faateodoro.nossacasadocodigo.repository;

import br.com.faateodoro.nossacasadocodigo.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);
    Optional<Livro> findByIsbn(String isbn);
}
