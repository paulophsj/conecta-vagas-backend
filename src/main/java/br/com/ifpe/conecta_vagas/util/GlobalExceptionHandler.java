package br.com.ifpe.conecta_vagas.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ifpe.conecta_vagas.util.exceptions.ChatException;
import br.com.ifpe.conecta_vagas.util.exceptions.VagaException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(VagaException.class)
    public ResponseEntity<Object> handleVagaException(VagaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", ex.getMessage(),
                        "status", HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(ChatException.class)
    public ResponseEntity<Object>  handleChatException(ChatException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "message", ex.getMessage(),
                        "status", HttpStatus.CONFLICT.value()
                ));
    }
}