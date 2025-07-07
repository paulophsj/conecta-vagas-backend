package br.com.ifpe.conecta_vagas.modelo.recrutador;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;

public interface RecrutadorRepository extends JpaRepository<Recrutador, Long> {
    Recrutador findByUsuario(Usuario usuario);
}
