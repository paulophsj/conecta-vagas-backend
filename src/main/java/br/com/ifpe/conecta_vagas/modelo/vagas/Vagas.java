package br.com.ifpe.conecta_vagas.modelo.vagas;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import br.com.ifpe.conecta_vagas.util.enums.vagas.TipoContrato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vagas extends EntidadeAuditavel {
    @ManyToOne
    @JsonIgnore
    private Recrutador recrutador;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = true, length = 1000)
    private String requisitos;

    @Column(nullable = true, length = 600)
    private String localizacao;

    @Column(nullable = true)
    private Double salario;

    @Column(nullable = false)
    private Boolean ativa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContrato tipoContrato;

    @Column(nullable = true, length = 999)
    private Integer cargaHoraria;

    @ManyToOne
    @JsonIgnore
    private Candidato candidato;
}
