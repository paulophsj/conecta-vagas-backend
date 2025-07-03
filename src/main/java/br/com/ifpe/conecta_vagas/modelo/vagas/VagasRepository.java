package br.com.ifpe.conecta_vagas.modelo.vagas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VagasRepository extends JpaRepository<Vagas, Long> {
    @Query(value = "SELECT v FROM Vagas v WHERE v.recrutador.id = :idRecrutador")
    List<Vagas> findAllVagas(Long idRecrutador);
}
