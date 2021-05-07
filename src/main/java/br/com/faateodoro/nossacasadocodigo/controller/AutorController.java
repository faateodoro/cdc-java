package br.com.faateodoro.nossacasadocodigo.controller;

import br.com.faateodoro.nossacasadocodigo.controller.dto.AutorRequest;
import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import br.com.faateodoro.nossacasadocodigo.repository.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autores")
    public List<Autor> visualizar(){
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }

    @PostMapping("/autores")
    public ResponseEntity<Autor> cadastrar(@RequestBody @Valid AutorRequest autorRequest,
           UriComponentsBuilder uriBuilder){

        Autor autor = autorRequest.toAutor();
        autorRepository.save(autor);

        return ResponseEntity.ok().body(autor);
    }
}
