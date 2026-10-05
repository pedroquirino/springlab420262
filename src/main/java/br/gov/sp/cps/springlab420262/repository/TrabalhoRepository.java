package br.gov.sp.cps.springlab420262.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.cps.springlab420262.entity.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    public List<Trabalho> findByAlunoRaAndTituloContainingIgnoreCase(Long ra, String titulo);

    @Query ("SELECT t FROM Trabalho t JOIN t.aluno a WHERE a.ra = :ra AND LOWER(t.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    public List<Trabalho> buscarPorRaAlunoETitulo(Long ra, String titulo);
    
}
