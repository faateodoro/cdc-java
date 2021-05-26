package br.com.faateodoro.nossacasadocodigo.controller;

import br.com.faateodoro.nossacasadocodigo.modelo.Categoria;
import br.com.faateodoro.nossacasadocodigo.repository.CategoriaRepository;
import br.com.faateodoro.nossacasadocodigo.validacao.exception.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categorias")
    public List<Categoria> visualizar(){
        return categoriaRepository.findAll();
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid Categoria categoria)
            throws MethodArgumentNotValidException{

        Optional<Categoria> categoriaJaExiste = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaJaExiste.isPresent())
            throw new ValidationException("Operação ilegal. Categoria já cadatrada no sistema!", "nome");

        categoriaRepository.save(categoria);
        return ResponseEntity.ok().body(categoria);
    }
}
