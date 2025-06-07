package br.com.ifpe.conecta_vagas.modelo.formacao_academica;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.util.enums.NivelAcademico;
import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
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
@SQLRestriction("habilitado = true")
@Table(name = "formacao_academica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormacaoAcademica extends EntidadeAuditavel{
    @ManyToOne
    @JsonIgnore
    private Candidato candidato;

    @Enumerated(EnumType.STRING)
    private NivelAcademico nivelAcademico;

    @Column
    private String curso;

    @Column
    private String instituicao;

    @Column
    private LocalDate anoConclusao;
}
