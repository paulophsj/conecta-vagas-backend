package br.com.ifpe.conecta_vagas.modelo.candidatura;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidatura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLRestriction("habilitado = true")
public class Candidatura extends EntidadeAuditavel{
    @ManyToOne
    @JoinColumn
    private Candidato candidato;

    @ManyToOne
    @JoinColumn
    private Vagas vagas;
}
