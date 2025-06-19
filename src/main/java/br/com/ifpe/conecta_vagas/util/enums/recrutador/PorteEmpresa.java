package br.com.ifpe.conecta_vagas.util.enums.recrutador;

public enum PorteEmpresa {
    MICRO("Microempresa"),
    PEQUENA("Pequena Empresa"),
    MEDIA("Média Empresa"),
    GRANDE("Grande Empresa");

    private final String descricao;

    PorteEmpresa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
