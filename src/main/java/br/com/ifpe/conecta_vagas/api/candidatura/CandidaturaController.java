package br.com.ifpe.conecta_vagas.api.candidatura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.candidato.CandidatoService;
import br.com.ifpe.conecta_vagas.modelo.candidatura.Candidatura;
import br.com.ifpe.conecta_vagas.modelo.candidatura.CandidaturaService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin
@RequestMapping("/api/candidatura")
public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public ResponseEntity<List<Candidatura>> findAllByCandidato(HttpServletRequest request) {
        List<Candidatura> candidatura = candidaturaService.findByCandidato(candidatoService.obterCandidatoLogado(request).getId());
        return new ResponseEntity<List<Candidatura>>(candidatura, HttpStatus.OK);
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable("id") Long idVaga, HttpServletRequest request) {
        boolean candidatoPosuiVaga = candidatoService.obterCandidatoLogado(request).getCandidaturas().stream().anyMatch(vaga -> vaga.getId().equals(idVaga));
        if(candidatoPosuiVaga){
            Map<String, Object> erro = new HashMap<>();
            erro.put("message", "Você Já está cadastrado nessa vaga");

            return new ResponseEntity<Map<String, Object>>(erro, HttpStatus.UNAUTHORIZED);
        }
        Candidatura candidatura = candidaturaService.save(candidatoService.obterCandidatoLogado(request).getId(), idVaga);
        return new ResponseEntity<Candidatura>(candidatura, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idVaga, HttpServletRequest request){
        boolean candidatoPosuiVaga = candidatoService.obterCandidatoLogado(request).getCandidaturas().stream().anyMatch(vaga -> vaga.getId().equals(idVaga));
        if(!candidatoPosuiVaga){
            Map<String, Object> erro = new HashMap<>();
            erro.put("message", "Você não possui uma vaga com esse id");

            return new ResponseEntity<Map<String, Object>>(erro, HttpStatus.UNAUTHORIZED);
        }
        candidaturaService.ExcluirCandidatura(candidatoService.obterCandidatoLogado(request).getId(), idVaga);
        return ResponseEntity.ok().build();
    }
    
}
