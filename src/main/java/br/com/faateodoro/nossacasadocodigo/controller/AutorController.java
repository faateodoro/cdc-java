package br.com.faateodoro.nossacasadocodigo.controller;

import br.com.faateodoro.nossacasadocodigo.controller.dto.AutorRequest;
import br.com.faateodoro.nossacasadocodigo.modelo.Autor;
import br.com.faateodoro.nossacasadocodigo.repository.AutorRepository;
import br.com.faateodoro.nossacasadocodigo.validacao.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
           UriComponentsBuilder uriBuilder) throws MethodArgumentNotValidException {

        Optional<Autor> emailJaExiste = autorRepository.findByEmail(autorRequest.getEmail());
        if(emailJaExiste.isPresent())
            throw new ValidationException("Operação ilegal. Email já cadatrado no sistema!", "email");

        Autor autor = autorRequest.toAutor();
        autorRepository.save(autor);

        return ResponseEntity.ok().body(autor);
    }
}
