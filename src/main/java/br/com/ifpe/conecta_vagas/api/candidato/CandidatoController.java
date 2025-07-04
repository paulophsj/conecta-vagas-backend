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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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

    @GetMapping
    public ResponseEntity<Candidato> encontrarSessao(HttpServletRequest request){
        Candidato candidato = this.candidatoService.obterCandidatoLogado(request);
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findOne(@PathVariable("id") Long id) {
        Candidato candidato = this.candidatoService.findOne(id);
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Candidato> save(@RequestBody @Valid CandidatoRequest request) {
        Candidato candidato = this.candidatoService.save(request.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> update(@PathVariable("id") Long id, @RequestBody @Valid CandidatoRequest candidatoRequest, HttpServletRequest request) {
        Candidato candidatoLogado = this.candidatoService.obterCandidatoLogado(request);

        if(id != candidatoLogado.getId()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Candidato candidato = this.candidatoService.update(id, candidatoRequest.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.candidatoService.delete(id);
        return ResponseEntity.ok().build();
    }

    /*
     * Controller > Endereco_Candidato
     */
    @GetMapping("/endereco/{id}")
    public ResponseEntity<List<EnderecoCandidato>> getEndereco(@PathVariable("id") Long id) {
        List<EnderecoCandidato> enderecoCandidato = this.candidatoService.findAllEndereco(id);
        return new ResponseEntity<List<EnderecoCandidato>>(enderecoCandidato, HttpStatus.OK);
    }

    @PostMapping("/endereco/{id}")
    public ResponseEntity<EnderecoCandidato> saveEndereco(@PathVariable("id") Long id,
            @RequestBody EnderecoCandidatoRequest request) {
        EnderecoCandidato enderecoCandidato = this.candidatoService.saveEndereco(id, request.build());
        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<EnderecoCandidato> updateEndereco(@PathVariable("id") Long id,
            @RequestBody EnderecoCandidatoRequest request) {
        EnderecoCandidato enderecoCandidato = this.candidatoService.updateEndereco(id, request.build());
        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable("id") Long id) {
        this.candidatoService.delete(id);
        return ResponseEntity.ok().build();
    }

    /*
     * Controller > Formacao_Candidato
     */
    @GetMapping("/formacao/{id}")
    public ResponseEntity<List<FormacaoAcademica>> getFormacao(@PathVariable("id") Long id) {
        List<FormacaoAcademica> formacaoAcademicas = this.candidatoService.findAllFormacao(id);
        return new ResponseEntity<List<FormacaoAcademica>>(formacaoAcademicas, HttpStatus.OK);
    }

    @PostMapping("/formacao/{id}")
    public ResponseEntity<FormacaoAcademica> saveFormacao(@PathVariable("id") Long id,
            @RequestBody FormacaoAcademicaRequest request) {
        FormacaoAcademica formacaoAcademica = this.candidatoService.saveFormacao(id, request.build());
        return new ResponseEntity<FormacaoAcademica>(formacaoAcademica, HttpStatus.OK);
    }

    @PutMapping("/formacao/{id}")
    public ResponseEntity<FormacaoAcademica> updateFormacao(@PathVariable("id") Long id,
            @RequestBody FormacaoAcademicaRequest request) {
        FormacaoAcademica formacaoAcademica = this.candidatoService.updateFormacao(id, request.build());
        return new ResponseEntity<FormacaoAcademica>(formacaoAcademica, HttpStatus.OK);
    }

    @DeleteMapping("/formacao/{id}")
    public ResponseEntity<Void> deleteFormacao(@PathVariable("id") Long id) {
        this.candidatoService.deleteFormacao(id);
        return ResponseEntity.ok().build();
    }
}
