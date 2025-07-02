package br.com.ifpe.conecta_vagas.api.mensagem;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
import br.com.ifpe.conecta_vagas.modelo.chat.ChatService;
import br.com.ifpe.conecta_vagas.modelo.mensagem.Mensagem;
import br.com.ifpe.conecta_vagas.modelo.mensagem.MensagemService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/mensagem")
public class MensagemController {
    @Autowired
    private MensagemService mensagemService;
    @Autowired
    private ChatService chatService;

    @PostMapping("/recrutador")
    public ResponseEntity<Mensagem> saveMensagemRecrutador(@RequestBody @Valid MensagemRequest request) {
        Chat chat = this.chatService.findOne(request.getIdChat());

        Mensagem novaMensagem = new Mensagem();

        novaMensagem.setConteudo(request.getConteudo());
        novaMensagem.setEnviadoPorCandidato(Boolean.FALSE);
        novaMensagem.setHoraMensagem(LocalDateTime.now());
        novaMensagem.setChat(chat);

        Mensagem salvarNovaMensagem = this.mensagemService.save(novaMensagem);

        return new ResponseEntity<Mensagem>(salvarNovaMensagem, HttpStatus.OK);
    }
    @PostMapping("/candidato")
    public ResponseEntity<Mensagem> saveMensagemCandidato(@RequestBody @Valid MensagemRequest request) {
        Chat chat = this.chatService.findOne(request.getIdChat());

        Mensagem novaMensagem = new Mensagem();

        novaMensagem.setConteudo(request.getConteudo());
        novaMensagem.setEnviadoPorCandidato(Boolean.TRUE);
        novaMensagem.setHoraMensagem(LocalDateTime.now());
        novaMensagem.setChat(chat);

        Mensagem salvarNovaMensagem = this.mensagemService.save(novaMensagem);

        return new ResponseEntity<Mensagem>(salvarNovaMensagem, HttpStatus.OK);
    }
    
}