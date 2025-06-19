package br.com.ifpe.conecta_vagas.util.enums.recrutador;

public enum Setores {
    TECNOLOGIA("Tecnologia"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    FINANCAS("Finanças"),
    INDUSTRIA("Indústria"),
    COMERCIO("Comércio"),
    SERVICOS("Serviços");

    private final String descricao;

    Setores(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
