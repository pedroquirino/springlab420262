package br.gov.sp.cps.springlab420262.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.cps.springlab420262.entity.Trabalho;
import br.gov.sp.cps.springlab420262.service.TrabalhoService;

@RestController 
@CrossOrigin 
@RequestMapping("/trabalho")
public class TrabalhoController {

    private final TrabalhoService service;

    public TrabalhoController(TrabalhoService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(View.TrabalhoView.class)
    public List<Trabalho> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    @JsonView(View.TrabalhoView.class)
    public ResponseEntity<Trabalho> cadastrar(@RequestBody Trabalho trabalho) {
        Trabalho trabalhoCadastrado = service.cadastrar(trabalho);
        return ResponseEntity.created(URI.create("/trabalho/" + trabalhoCadastrado.getId())).body(trabalhoCadastrado);
    }

    @GetMapping("/buscar")
    @JsonView(View.TrabalhoView.class)
    public List<Trabalho> buscarPorRaAlunoETitulo(@RequestParam("ra") Long ra, @RequestParam("titulo") String titulo) {
        return service.buscarPorRaAlunoETitulo(ra, titulo);
    }
    
}
