package br.com.ifpe.conecta_vagas.api.recrutador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/recrutador")
public class RecrutadorController {
    @Autowired
    private RecrutadorService recrutadorService;

    @PostMapping
    public ResponseEntity<Recrutador> save(@RequestBody @Valid RecrutadorRequest request) {
        Recrutador recrutador = this.recrutadorService.save(request.build());
        return new ResponseEntity<>(recrutador, HttpStatus.CREATED);
    }

}
