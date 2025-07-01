package br.com.ifpe.conecta_vagas.modelo.candidato;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
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

    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 100)
    private String cargoPretendido;

    @Column
    private Double pretensaoSalarial;

    @Column(length = 500)
    private String resumoProfissional;

    @Column(unique = true, nullable = true, length = 15)
    private String numeroTelefone;

    @Column(nullable = false, length = 50)
    private String senha;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "candidato")
    @JsonIgnore
    private List<Chat> chats;
}
