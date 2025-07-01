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

    @Transactional
    public Chat save(Candidato candidato, Recrutador recrutador) {
        Chat hasChat = this.chatRepository.findOne()

        Chat chat = new Chat();
        chat.setCandidato(candidato);
        chat.setRecrutador(recrutador);

        return this.chatRepository.save(chat);
    }
}
