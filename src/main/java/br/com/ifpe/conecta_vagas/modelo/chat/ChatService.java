package br.com.ifpe.conecta_vagas.modelo.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.util.exceptions.ChatException;
import jakarta.transaction.Transactional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> findAllByRecrutador(Recrutador recrutador){
        return chatRepository.findByRecrutador(recrutador);
    }

    public List<Chat> findAllByCandidato(Candidato candidato){
        return chatRepository.findByCandidato(candidato);
    }

    public Chat findOne(Long id){
        return this.chatRepository.findById(id).get();
    }

    @Transactional
    public Chat save(Candidato candidato, Recrutador recrutador) {
        boolean hasChat = this.chatRepository.existsByCandidatoAndRecrutador(candidato, recrutador);

        if(hasChat){
            throw new ChatException(ChatException.EXISTS_CHAT);
        }

        Chat chat = new Chat();
        chat.setCandidato(candidato);
        chat.setRecrutador(recrutador);
        chat.setHabilitado(Boolean.TRUE);

        return this.chatRepository.save(chat);
    }
}