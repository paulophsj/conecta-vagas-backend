package br.com.ifpe.conecta_vagas.modelo.candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;


public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Candidato findByUsuario(Usuario usuario);

    boolean existsByUsuario_Username(String usuario_Username);
    boolean existsByCpf(String cpf);
    boolean existsByNumeroTelefone(String numeroTelefone);
}
