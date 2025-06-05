package br.com.ifpe.conecta_vagas.modelo.entity;

import java.time.LocalDate;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class EntidadeAuditavel extends EntidadeNegocio {
    
    private Long versao;

    private LocalDate dataCriacao;

    private LocalDate dataUltimaModificacao;

    private Long criadoPor; // Id do usuário que o criou

    private Long ultimaModificacaoPor; // Id do usuário que fez a última alteração

}
