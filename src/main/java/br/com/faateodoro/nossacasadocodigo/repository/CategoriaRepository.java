package br.com.faateodoro.nossacasadocodigo.repository;

import br.com.faateodoro.nossacasadocodigo.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}
