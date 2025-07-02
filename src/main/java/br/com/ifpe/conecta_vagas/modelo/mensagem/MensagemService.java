package br.com.ifpe.conecta_vagas.modelo.mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class MensagemService {
    @Autowired
    private MensagemRepository mensagemRepository;

    @Transactional
    public Mensagem save(Mensagem mensagem){
        mensagem.setHabilitado(Boolean.TRUE);
        return this.mensagemRepository.save(mensagem);
    }
}