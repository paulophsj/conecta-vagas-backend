package br.com.ifpe.conecta_vagas.api.candidato;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Candidato> encontrarSessao(HttpServletRequest request) {
        Candidato candidato = this.candidatoService.obterCandidatoLogado(request);
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Candidato> save(@RequestBody @Valid CandidatoRequest request) {
        Candidato candidato = this.candidatoService.save(request.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Candidato> update(@RequestBody @Valid CandidatoUpdateRequest candidatoRequest,
            HttpServletRequest request) {
        Candidato candidato = this.candidatoService.update(candidatoService.obterCandidatoLogado(request).getId(),
                candidatoRequest.build());
        return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpServletRequest request) {

        if (!id.equals(this.candidatoService.obterCandidatoLogado(request))) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este candidato");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }

        this.candidatoService.delete(id);
        return ResponseEntity.ok().build();
    }

    /*
     * Controller > Endereco_Candidato
     */
    @GetMapping("/endereco/{id}")
    public ResponseEntity<?> getOneEndereco(@PathVariable("id") Long id, HttpServletRequest request) {
        Candidato candidato = candidatoService.obterCandidatoLogado(request);

        List<EnderecoCandidato> allEnderecos = candidatoService.findAllEndereco(candidato.getId());

        boolean existsEndereco = allEnderecos.stream().anyMatch(endereco -> endereco.getId().equals(id));

        if (!existsEndereco) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este endereço");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }

        EnderecoCandidato enderecoCandidato = candidatoService.findOneEndereco(id);

        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<EnderecoCandidato>> getEndereco(HttpServletRequest request) {
        List<EnderecoCandidato> enderecoCandidato = this.candidatoService
                .findAllEndereco(candidatoService.obterCandidatoLogado(request).getId());
        return new ResponseEntity<List<EnderecoCandidato>>(enderecoCandidato, HttpStatus.OK);
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoCandidato> saveEndereco(
            @RequestBody EnderecoCandidatoRequest enderecoCandidatoRequest, HttpServletRequest request) {
        EnderecoCandidato enderecoCandidato = this.candidatoService
                .saveEndereco(candidatoService.obterCandidatoLogado(request).getId(), enderecoCandidatoRequest.build());
        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<?> updateEndereco(@PathVariable("id") Long id,
            @RequestBody EnderecoCandidatoRequest enderecoCandidatoRequest, HttpServletRequest request) {

        boolean candidatoPossuiEndereco = candidatoService.obterCandidatoLogado(request).getEnderecos()
                .stream()
                .anyMatch(endereco -> endereco.getId().equals(id));

        if (!candidatoPossuiEndereco) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este endereco");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        EnderecoCandidato enderecoCandidato = this.candidatoService.updateEndereco(id,
                enderecoCandidatoRequest.build());
        return new ResponseEntity<EnderecoCandidato>(enderecoCandidato, HttpStatus.OK);
    }

    @DeleteMapping("/endereco/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable("id") Long id, HttpServletRequest request) {
        boolean candidatoPossuiEndereco = candidatoService.obterCandidatoLogado(request).getEnderecos().stream()
                .anyMatch(endereco -> endereco.getId().equals(id));
        if (!candidatoPossuiEndereco) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este candidato");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        this.candidatoService.delete(id);
        return ResponseEntity.ok().build();
    }

    /*
     * Controller > Formacao_Candidato
     */
    @GetMapping("/formacao/{id}")
    public ResponseEntity<?> getOneFormacao(@PathVariable("id") Long id, HttpServletRequest request) {
        Candidato candidato = candidatoService.obterCandidatoLogado(request);

        List<FormacaoAcademica> allFormacaoAcademicas = candidatoService.findAllFormacao(candidato.getId());

        boolean existsEndereco = allFormacaoAcademicas.stream().anyMatch(formacao -> formacao.getId().equals(id));

        if (!existsEndereco) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar esta formação");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }

        FormacaoAcademica formacaoAcademica = candidatoService.findOneFormacao(id);

        return new ResponseEntity<FormacaoAcademica>(formacaoAcademica, HttpStatus.OK);
    }

    @GetMapping("/formacao")
    public ResponseEntity<List<FormacaoAcademica>> getFormacao(HttpServletRequest request) {
        List<FormacaoAcademica> formacaoAcademicas = this.candidatoService
                .findAllFormacao(candidatoService.obterCandidatoLogado(request).getId());
        return new ResponseEntity<List<FormacaoAcademica>>(formacaoAcademicas, HttpStatus.OK);
    }

    @PostMapping("/formacao")
    public ResponseEntity<FormacaoAcademica> saveFormacao(
            @RequestBody FormacaoAcademicaRequest formacaoAcademicaRequest,
            HttpServletRequest request) {
        FormacaoAcademica formacaoAcademica = this.candidatoService
                .saveFormacao(candidatoService.obterCandidatoLogado(request).getId(), formacaoAcademicaRequest.build());
        return new ResponseEntity<FormacaoAcademica>(formacaoAcademica, HttpStatus.OK);
    }

    @PutMapping("/formacao/{id}")
    public ResponseEntity<?> updateFormacao(@PathVariable("id") Long id,
            @RequestBody FormacaoAcademicaRequest formacaoAcademicaRequest, HttpServletRequest request) {
        boolean candidatoPossuiFormacao = candidatoService.obterCandidatoLogado(request).getEnderecos().stream()
                .anyMatch(endereco -> endereco.getId().equals(id));
        if (!candidatoPossuiFormacao) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este candidato");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        FormacaoAcademica formacaoAcademica = this.candidatoService.updateFormacao(id,
                formacaoAcademicaRequest.build());
        return new ResponseEntity<FormacaoAcademica>(formacaoAcademica, HttpStatus.OK);
    }

    @DeleteMapping("/formacao/{id}")
    public ResponseEntity<?> deleteFormacao(@PathVariable("id") Long id, HttpServletRequest request) {
        boolean candidatoPossuiFormacao = candidatoService.obterCandidatoLogado(request).getEnderecos().stream()
                .anyMatch(endereco -> endereco.getId().equals(id));

        if (!candidatoPossuiFormacao) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar este candidato");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        this.candidatoService.deleteFormacao(id);
        return ResponseEntity.ok().build();
    }
}
