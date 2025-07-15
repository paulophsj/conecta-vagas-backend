package br.com.ifpe.conecta_vagas.util.exceptions;

public class CandidatoException extends RuntimeException {

    public static final String APENAS_LETRAS = "O campo '%s' deve conter apenas letras.";
    public static final String APENAS_NUMEROS = "O campo '%s' deve conter apenas números.";
    public static final String FORMATO_CEP =  "O campo 'CEP' deve estar no formato 00000-000.";

    public static final String CPF_REPETIDO = "Já existe um CPF cadastrado.";
    public static final String TELEFONE_REPETIDO = "Já existe um telefone cadastrado";

    public static final String ENDERECO_NAO_ENCONTRADO = "Endereço não localizado";

    public CandidatoException(String message, String campo) {
        super(String.format(message, campo));
    }

    public CandidatoException(String message) {
        super(String.format(message));
    }
}
