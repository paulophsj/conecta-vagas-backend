package br.com.ifpe.conecta_vagas.modelo.candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;


public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Candidato findByUsuario(Usuario usuario);

    boolean existsByCpf(String cpf);
    boolean existsByNumeroTelefone(String numeroTelefone);
}
