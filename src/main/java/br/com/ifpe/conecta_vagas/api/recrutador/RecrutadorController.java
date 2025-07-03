package br.com.ifpe.conecta_vagas.api.recrutador;

import java.util.List;

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
    public ResponseEntity<Recrutador> save(@RequestBody @Valid RecrutadorRequest request) {
        Recrutador recrutador = this.recrutadorService.save(request.build());
        return new ResponseEntity<Recrutador>(recrutador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recrutador> update(@PathVariable("id") Long id,
            @RequestBody @Valid RecrutadorRequest request) {
        Recrutador novoRecrutador = this.recrutadorService.update(id, request.build());
        return new ResponseEntity<Recrutador>(novoRecrutador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.recrutadorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recrutador> findOne(@PathVariable("id") Long id) {
        Recrutador recrutador = this.recrutadorService.findOne(id);
        return new ResponseEntity<Recrutador>(recrutador, HttpStatus.OK);
    }

    // Todas as vagas de um recrutador
    @GetMapping("/vagas/{id}") // Todas as vagas de um recrutador
    public ResponseEntity<List<Vagas>> findAll(@PathVariable("id") Long id) {
        List<Vagas> vaga = this.vagasService.findAllVagas(id);
        return new ResponseEntity<List<Vagas>>(vaga, HttpStatus.OK);
    }
}
