package br.com.faateodoro.nossacasadocodigo.controller;

import br.com.faateodoro.nossacasadocodigo.controller.dto.AutorForm;
import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import br.com.faateodoro.nossacasadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public List<Autor> visualizarTodos(){
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }
    @PostMapping
    public ResponseEntity<Autor> cadastrar(@RequestBody AutorForm autorForm,
                                           UriComponentsBuilder uriBuilder){
        Autor autor = new Autor();
        try{
            autor = autorForm.toAutor();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

        autorRepository.save(autor);
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(autor);
    }
}
