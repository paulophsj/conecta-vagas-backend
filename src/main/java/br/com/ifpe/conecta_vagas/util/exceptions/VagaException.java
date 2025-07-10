package br.com.ifpe.conecta_vagas.util.exceptions;

public class VagaException extends RuntimeException {
    public static final String VAGA_NAO_ENCONTRADA = "Vaga não encontrada";

    public VagaException(String message) {
        super(String.format(message));
    }
}
