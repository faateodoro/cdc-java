package br.com.faateodoro.nossacasadocodigo.repository;

import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail(String email);
}
