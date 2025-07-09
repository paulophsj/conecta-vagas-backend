package br.com.ifpe.conecta_vagas.modelo.chat;

import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.mensagem.Mensagem;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLRestriction("habilitado = true")
public class Chat extends EntidadeAuditavel{
    @OneToMany(mappedBy = "chat")
    private List<Mensagem> mensagens;

    @ManyToOne
    private Recrutador recrutador;

    @ManyToOne
    private Candidato candidato;
}
