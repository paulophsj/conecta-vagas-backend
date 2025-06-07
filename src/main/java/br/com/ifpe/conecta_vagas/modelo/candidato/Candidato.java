package br.com.ifpe.conecta_vagas.modelo.candidato;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLRestriction("habilitado = true")
@Table(name = "candidato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Candidato extends EntidadeAuditavel {
    @OneToMany(mappedBy = "candidato", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FormacaoAcademica> formacaoAcademica;

    @OneToMany(mappedBy = "candidato", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<EnderecoCandidato> enderecos;

    @Column
    private String cpf;

    @Column
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String cargoPretendido;

    @Column
    private double pretensaoSalarial;

    @Column
    private String resumoProfissional;

    @Column
    private String numeroTelefone;

}
