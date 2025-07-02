package br.com.ifpe.conecta_vagas.modelo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import jakarta.transaction.Transactional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public Chat findOne(Long id){
        return this.chatRepository.findById(id).get();
    }

    @Transactional
    public Chat save(Candidato candidato, Recrutador recrutador) {
        boolean hasChat = this.chatRepository.existsByCandidatoAndRecrutador(candidato, recrutador);

        if(hasChat){
            throw new IllegalStateException("Já existe um chat ativo.");
        }

        Chat chat = new Chat();
        chat.setCandidato(candidato);
        chat.setRecrutador(recrutador);
        chat.setHabilitado(Boolean.TRUE);

        return this.chatRepository.save(chat);
    }
}