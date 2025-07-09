package br.com.ifpe.conecta_vagas.util.enums.vagas;

public enum Formato {
    PRESENCIAL("Presencial"),
    HIBRIDO("Hibrido"),
    REMOTO("Remoto");

    private String formato;

    private Formato(String formato){
        this.formato = formato;
    }
    public String getFormato(){
        return formato;
    }

}
