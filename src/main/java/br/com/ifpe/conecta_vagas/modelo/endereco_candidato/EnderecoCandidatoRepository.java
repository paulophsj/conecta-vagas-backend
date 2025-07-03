package br.com.ifpe.conecta_vagas.modelo.endereco_candidato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoCandidatoRepository extends JpaRepository<EnderecoCandidato, Long>{
    @Query(value = "SELECT e FROM EnderecoCandidato e WHERE e.candidato.id = :idCandidato")
    List<EnderecoCandidato> findAllByIdCandidato(Long idCandidato);
}
