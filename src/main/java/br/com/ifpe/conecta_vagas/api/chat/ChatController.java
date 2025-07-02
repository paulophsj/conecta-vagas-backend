package br.com.ifpe.conecta_vagas.api.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.candidato.CandidatoService;
import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
import br.com.ifpe.conecta_vagas.modelo.chat.ChatService;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private RecrutadorService recrutadorService;

    @PostMapping
    public ResponseEntity<Chat> save(@RequestBody ChatRequest request) {
        Candidato candidato = this.candidatoService.findOne(request.getIdCandidato());
        Recrutador recrutador = this.recrutadorService.findOne(request.getIdRecrutador());

        Chat novoChat = this.chatService.save(candidato, recrutador);
        return new ResponseEntity<Chat>(novoChat, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Chat> findOne(@PathVariable("id") Long id) {
        Chat chat = this.chatService.findOne(id);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }
    
    
}