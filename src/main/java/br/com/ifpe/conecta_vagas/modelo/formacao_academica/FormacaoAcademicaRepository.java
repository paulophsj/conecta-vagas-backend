package br.com.ifpe.conecta_vagas.modelo.formacao_academica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FormacaoAcademicaRepository extends JpaRepository<FormacaoAcademica, Long> {
    @Query(value = "SELECT f FROM FormacaoAcademica f WHERE f.candidato.id = :idCandidato")
    List<FormacaoAcademica> findAllByIdCandidato(Long idCandidato);
}
