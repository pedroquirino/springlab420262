package br.gov.sp.cps.springlab420262.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.cps.springlab420262.entity.Trabalho;
import br.gov.sp.cps.springlab420262.repository.TrabalhoRepository;

@Service 
public class TrabalhoServiceImpl implements TrabalhoService {

    private final TrabalhoRepository repo;

    private final AlunoService alunoService;

    public TrabalhoServiceImpl(TrabalhoRepository repo, AlunoService alunoService) {
        this.repo = repo;
        this.alunoService = alunoService;
    }

    @Override
    public List<Trabalho> buscarTodos() {
        return repo.findAll();
    }

    @Override
    @Transactional 
    public Trabalho cadastrar(Trabalho trabalho) {
        if(trabalho == null ||
                trabalho.getId() != null ||
                trabalho.getTitulo() == null || 
                trabalho.getTitulo().isBlank() ||
                trabalho.getAluno() == null ||
                trabalho.getAluno().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trabalho inválido");
        }
        if(trabalho.getDataHoraEntrega() == null) {
            trabalho.setDataHoraEntrega(LocalDateTime.now());
        }
        // Verifica se o aluno existe
        trabalho.setAluno(alunoService.buscarPorId(trabalho.getAluno().getId()));
        return repo.save(trabalho);
    }

    @Override
    public List<Trabalho> buscarPorRaAlunoETitulo(Long ra, String titulo) {
        if(ra == null || ra <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RA inválido");
        }
        if(titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título inválido");
        }
        return repo.buscarPorRaAlunoETitulo(ra, titulo);
    }
    
}
