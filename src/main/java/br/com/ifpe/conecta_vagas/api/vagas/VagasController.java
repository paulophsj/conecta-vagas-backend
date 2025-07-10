package br.com.ifpe.conecta_vagas.api.vagas;

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

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.modelo.vagas.VagasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
@RequestMapping("/api/vagas")
public class VagasController {
    @Autowired
    private VagasService vagasService;
    @Autowired
    private RecrutadorService recrutadorService;

    @GetMapping
    public ResponseEntity<List<Vagas>> findAll(){
        List<Vagas> todasVagas = vagasService.findAllVagas();
        return new ResponseEntity<List<Vagas>>(todasVagas, HttpStatus.OK);
    }

    @GetMapping("/recrutador")
    public ResponseEntity<List<Vagas>> findAllByRecrutador(HttpServletRequest request) {
        List<Vagas> vagas = this.vagasService.findAllVagasByRecrutador(recrutadorService.obterRecrutadorLogado(request).getId());
        return new ResponseEntity<List<Vagas>>(vagas, HttpStatus.OK);
    }
    

    @GetMapping("/{id}") // Uma única vaga
    public ResponseEntity<Vagas> findOne(@PathVariable("id") Long id) {
        Vagas vaga = this.vagasService.findOne(id);
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vagas> save(@RequestBody @Valid VagasRequest vagasRequest, HttpServletRequest request) {
        Recrutador recrutador = recrutadorService.obterRecrutadorLogado(request);
        vagasRequest.setNomeEmpresa(recrutador.getNomeEmpresa());

        Vagas vaga = this.vagasService.save(recrutador.getId(),
                vagasRequest.build());
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid VagasRequest vagasRequest,
            HttpServletRequest request) {
        boolean recrutadorPossuiVaga = recrutadorService.obterRecrutadorLogado(request).getVagas().stream()
                .anyMatch(vaga -> vaga.getId().equals(id));
        if (!recrutadorPossuiVaga) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar essa vaga");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        Vagas vaga = this.vagasService.update(id, vagasRequest.build());
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id, HttpServletRequest request) {
        boolean recrutadorPossuiVaga = recrutadorService.obterRecrutadorLogado(request).getVagas().stream()
                .anyMatch(vaga -> vaga.getId().equals(id));
        if (!recrutadorPossuiVaga) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Você não tem permissão para alterar essa vaga");
            return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
        }
        this.vagasService.remove(id);
        return ResponseEntity.ok().build();
    }
}
