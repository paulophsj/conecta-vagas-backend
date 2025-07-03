package br.com.ifpe.conecta_vagas.util.enums.formacao_academica;

public enum NivelAcademico {
    ENSINO_FUNDAMENTAL("Ensino Fundamental"),
    ENSINO_MEDIO("Ensino Médio"),
    TECNICO("Técnico"),
    TECNOLOGO("Tecnólogo"),
    SUPERIOR("Superior"),
    POS_GRADUACAO("Pós-Graduação"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private final String descricao;

    NivelAcademico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
