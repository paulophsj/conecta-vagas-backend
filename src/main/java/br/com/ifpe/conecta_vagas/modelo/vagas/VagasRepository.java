package br.com.ifpe.conecta_vagas.modelo.vagas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VagasRepository extends JpaRepository<Vagas, Long> {
    
}
