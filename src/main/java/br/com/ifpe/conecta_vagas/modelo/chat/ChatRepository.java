package br.com.ifpe.conecta_vagas.modelo.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Long> {
    boolean existsByCandidatoAndRecrutador(Candidato candidato, Recrutador recrutador);

    List<Chat> findByRecrutador(Recrutador recrutador);
    
    List<Chat> findByCandidato(Candidato candidato);
}