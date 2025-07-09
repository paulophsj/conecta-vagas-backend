package br.com.ifpe.conecta_vagas.modelo.mensagem;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
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
@Table(name = "mensagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLRestriction("habilitado = true")
public class Mensagem extends EntidadeAuditavel{
    @Column(nullable = false, length = 1000)
    private String conteudo;

    @Column
    private LocalDateTime horaMensagem;

    @Column(nullable = false)
    private Boolean enviadoPorCandidato;
    
    @ManyToOne
    @JsonIgnore
    private Chat chat;
}
