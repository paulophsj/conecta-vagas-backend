package br.com.ifpe.conecta_vagas.modelo.acesso;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;

import br.com.ifpe.conecta_vagas.util.entity.EntidadeNegocio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Perfil")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends EntidadeNegocio implements GrantedAuthority {
  
   public static final String ROLE_CANDIDATO = "CANDIDATO";
   public static final String ROLE_RECRUTADOR = "ROLE_RECRUTADOR";
  
   private String nome;
  
   @Override
   public String getAuthority() {
       return this.nome;
   }
  
}
