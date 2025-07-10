package br.com.ifpe.conecta_vagas.util.exceptions;

public class ChatException extends RuntimeException {
    public static final String EXISTS_CHAT = "Você já possui um chat com esse candidato.";

    public ChatException(String message){
        super(String.format(message));
    }
}
