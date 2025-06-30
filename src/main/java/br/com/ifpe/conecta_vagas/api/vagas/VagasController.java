package br.com.ifpe.conecta_vagas.api.vagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.modelo.vagas.VagasService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Vagas> findOne(@PathVariable("id") Long id){
        Vagas vaga = this.vagasService.findOne(id);
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Vagas> save(@RequestBody @Valid VagasRequest request) {
        Vagas vaga = this.vagasService.save(request.build());
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Vagas> update(@PathVariable("id") Long id, @RequestBody @Valid VagasRequest request) {
        Vagas vaga = this.vagasService.update(id, request.build());
        return new ResponseEntity<Vagas>(vaga, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id){
        this.vagasService.remove(id);
        return ResponseEntity.ok().build();
    }
}
