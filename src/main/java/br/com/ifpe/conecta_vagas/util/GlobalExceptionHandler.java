package br.com.ifpe.conecta_vagas.util;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = "Erro: Já existe um registro com esse valor único.";

        if (ex.getCause() != null && ex.getCause().getMessage().contains("constraint")) {
            String campoRepetido = ex.getCause().getMessage().split("Detalhe: Key ")[1].split("=")[0].replace("(", "").replace(")", "");
            errorMessage = "O campo " + ((campoRepetido.equals("username") ? "EMAIL" : campoRepetido.toUpperCase().replace("_", " ")) + " já está cadastrado. Utilize outro.");
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT) // HTTP 409 Conflict
                .body(Map.of(
                    "message", errorMessage,
                    "status", HttpStatus.CONFLICT.value()
                ));
    }
}