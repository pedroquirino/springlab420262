package br.gov.sp.cps.springlab420262.service;

import java.util.List;

import br.gov.sp.cps.springlab420262.entity.Trabalho;

public interface TrabalhoService {

    public List<Trabalho> buscarTodos();

    public Trabalho cadastrar(Trabalho trabalho);

    public List<Trabalho> buscarPorRaAlunoETitulo(Long ra, String titulo);
    
}
