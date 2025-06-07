package br.com.ifpe.conecta_vagas.modelo.endereco_candidato;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endereco_candidato")
@SQLRestriction("habilitado = true")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoCandidato extends EntidadeAuditavel {
    @ManyToOne
    @JsonIgnore
    private Candidato candidato;

    @Column
    private int enderecoNumero;

    @Column
    private String enderecoRua;

    @Column
    private String enderecoBairro;

    @Column
    private String enderecoCidade;

    @Column
    private String enderecoEstado;
}
