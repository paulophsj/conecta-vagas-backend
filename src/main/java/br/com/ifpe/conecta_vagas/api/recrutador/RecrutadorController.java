package br.com.ifpe.conecta_vagas.api.recrutador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.modelo.vagas.VagasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/recrutador")
public class RecrutadorController {
    @Autowired
    private RecrutadorService recrutadorService;
    @Autowired
    private VagasService vagasService;

    @PostMapping
    public ResponseEntity<Recrutador> save(@RequestBody @Valid RecrutadorRequest recrutadorRequest) {
        Recrutador recrutador = this.recrutadorService.save(recrutadorRequest.build());
        return new ResponseEntity<Recrutador>(recrutador, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Recrutador> update(@RequestBody @Valid RecrutadorRequest recrutadorRequest, HttpServletRequest request) {
        Recrutador novoRecrutador = this.recrutadorService.update(recrutadorService.obterRecrutadorLogado(request).getId(), recrutadorRequest.build());
        return new ResponseEntity<Recrutador>(novoRecrutador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpServletRequest request) {
        if(!id.equals(recrutadorService.obterRecrutadorLogado(request).getId())){
            Map<String, Object> erros = new HashMap<>();
            erros.put("message", "Você não tem permissão para alterar esse recrutador.");
            return new ResponseEntity<Map<String, Object>>(erros, HttpStatus.UNAUTHORIZED);
        }
        this.recrutadorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Recrutador> encontrarSessao(HttpServletRequest request) {
        Recrutador recrutador = this.recrutadorService.findOne(recrutadorService.obterRecrutadorLogado(request).getId());
        return new ResponseEntity<Recrutador>(recrutador, HttpStatus.OK);
    }

    // Todas as vagas de um recrutador
    @GetMapping("/vagas/{id}")
    public ResponseEntity<List<Vagas>> findAll(@PathVariable("id") Long id) {
        List<Vagas> vaga = this.vagasService.findAllVagasByRecrutador(id);
        return new ResponseEntity<List<Vagas>>(vaga, HttpStatus.OK);
    }
}
