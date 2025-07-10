package br.com.ifpe.conecta_vagas.modelo.candidatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;




public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{
    List<Candidatura> findByCandidato(Candidato candidato);
    
    @Query(value = "SELECT c FROM Candidatura c WHERE c.candidato.id = :idCandidato AND c.vagas.id = :idVaga")
    Candidatura findByIdCandidatoAndIdVaga(Long idCandidato, Long idVaga);

    boolean existsByCandidatoAndVagas(Candidato candidato, Vagas vagas);

    List<Candidatura> findByVagas(Vagas vagas);
}
