package br.com.ifpe.conecta_vagas.api.candidato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.api.endereco_candidato.EnderecoCandidatoRequest;
import br.com.ifpe.conecta_vagas.api.formacao_academica.FormacaoAcademicaRequest;
import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.candidato.CandidatoService;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin
@RequestMapping("/api/candidato")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    //FindAll
    @GetMapping
    public ResponseEntity<List<Candidato>> findAll() {
        List<Candidato> candidatos = this.candidatoService.findAll();
        return new ResponseEntity<List<Candidato>>(candidatos, HttpStatus.OK);
    }

    //FindOne
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findOne(@PathVariable("id") Long id) {
        Candidato candidato = this.candidatoService.findOne(id);
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    //Save
    @PostMapping
    public ResponseEntity<Candidato> save(@RequestBody CandidatoRequest request) {
        Candidato candidato = this.candidatoService.save(request.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Candidato> update(@PathVariable("id") Long id, @RequestBody CandidatoRequest request){
        Candidato candidato = this.candidatoService.update(id, request.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.candidatoService.delete(id);
        return ResponseEntity.ok().build();
    }
    /*
     * Controller > Endereco_Candidato
     */
    @PostMapping("/endereco/{id}")
    public ResponseEntity<EnderecoCandidato> saveEndereco(@PathVariable("id") Long id, @RequestBody EnderecoCandidatoRequest request){
        EnderecoCandidato enderecoCandidato = this.candidatoService.saveEndereco(id,request.build());
        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }
    @PostMapping("/formacao/{id}")
    public ResponseEntity<FormacaoAcademica> saveFormacao(@PathVariable("id") Long id, @RequestBody FormacaoAcademicaRequest request){
        FormacaoAcademica formacaoAcademica = this.candidatoService.saveFormacao(id,request.build());
        return new ResponseEntity<>(formacaoAcademica, HttpStatus.OK);
    }
}
