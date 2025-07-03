package br.com.ifpe.conecta_vagas.modelo.recrutador;

import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;
import br.com.ifpe.conecta_vagas.modelo.chat.Chat;
import br.com.ifpe.conecta_vagas.util.entity.EntidadeAuditavel;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.PorteEmpresa;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.Setores;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@SQLRestriction("habilitado = true")
@Table(name = "recrutador")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Recrutador extends EntidadeAuditavel {
    @OneToOne
   @JoinColumn(nullable = false)
   private Usuario usuario;

    @Column(unique = true, nullable = false, length = 18)
    private String cnpj;

    //Comentario
    @Column(nullable = false)
    private String nomeEmpresa;

    @Column(unique = true, nullable = true, length = 15)
    private String numeroTelefone;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 500, nullable = true)
    private String descricaoEmpresa;

    @Column(length = 4, nullable = true)
    private String anoFundacao;

    @Column(nullable = false)
    private PorteEmpresa porteEmpresa;

    @Column(nullable = true)
    private Number numeroFuncionarios;

    @Column(nullable = false)
    private Setores setorEmpresa;

    @OneToMany(mappedBy = "recrutador")
    @JsonIgnore
    private List<Chat> chats;
}
