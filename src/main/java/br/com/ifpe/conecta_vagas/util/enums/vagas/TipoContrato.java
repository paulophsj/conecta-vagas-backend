package main.java.br.com.ifpe.conecta_vagas.util.enums.vagas;

public enum TipoContrato {
    CLT("CLT"),
    PJ("PJ"),
    FREELANCER("Freelancer"),
    MEI("MEI"),
    VOLUNTARIO("Voluntário"),
    APRENDIZ("Aprendiz"),
    INDERTEMINADO("Indeterminado"),
    ESTAGIO("Estágio");

    private final String tipo;

    private TipoContrato(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
