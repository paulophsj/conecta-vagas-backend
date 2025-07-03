package br.com.ifpe.conecta_vagas.api.websocket;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import br.com.ifpe.conecta_vagas.api.mensagem.MensagemRequest;
import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
import br.com.ifpe.conecta_vagas.modelo.chat.ChatService;
import br.com.ifpe.conecta_vagas.modelo.mensagem.Mensagem;
import br.com.ifpe.conecta_vagas.modelo.mensagem.MensagemService;

@Controller
public class WebSocketMensagemController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MensagemService mensagemService;

    public WebSocketMensagemController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/mensagem") // Entra aqui via /api/mensagem
    public void enviar(MensagemRequest message) throws Exception {
        Chat chat = this.chatService.findOne(message.getIdChat());

        Mensagem novaMensagem = new Mensagem();
        novaMensagem.setConteudo(message.getConteudo());
        novaMensagem.setEnviadoPorCandidato(message.getEnviadoPorCandidato());
        novaMensagem.setHoraMensagem(LocalDateTime.now());
        novaMensagem.setChat(chat);

        Mensagem mensagemSalva = this.mensagemService.save(novaMensagem);

        // Notifica todos que estão escutando o canal
        messagingTemplate.convertAndSend("/chat/" + message.getIdChat(), mensagemSalva);
    }
}
