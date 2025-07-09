package br.com.ifpe.conecta_vagas.modelo.candidatura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import br.com.ifpe.conecta_vagas.modelo.candidato.CandidatoService;
import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.modelo.vagas.VagasService;
import jakarta.transaction.Transactional;

@Service
public class CandidaturaService {
    @Autowired
    private CandidaturaRepository candidaturaRepository;
    @Autowired
    private VagasService vagasService;
    @Autowired
    private CandidatoService candidatoService;

    public boolean existsByCandidatoAndVagas(Long idVaga, Long idCandidato){
        Candidato candidato = candidatoService.findOne(idCandidato);
        Vagas vagas = vagasService.findOne(idVaga);

        return candidaturaRepository.existsByCandidatoAndVagas(candidato, vagas);
    }
    public Candidatura findByIdCandidatoAndIdVaga(Long idCandidato, Long idVaga){
        return candidaturaRepository.findByIdCandidatoAndIdVaga(idCandidato, idVaga);
    }
    @Transactional
    public Candidatura save(Long idCandidato, Long idVaga){
        Vagas vaga = vagasService.findOne(idVaga);
        Candidato candidato = candidatoService.findOne(idCandidato);

        Candidatura novaCandidatura = new Candidatura();
        novaCandidatura.setCandidato(candidato);
        novaCandidatura.setVagas(vaga);
        novaCandidatura.setHabilitado(Boolean.TRUE);

        return candidaturaRepository.save(novaCandidatura);
    }
    public List<Candidatura> findByCandidato(Long idCandidato){
        Candidato candidato = candidatoService.findOne(idCandidato);

        return candidaturaRepository.findByCandidato(candidato);
    }
    @Transactional
    public void ExcluirCandidatura(Long idCandidato, Long idVaga){
        Candidatura candidatura = findByIdCandidatoAndIdVaga(idCandidato, idVaga);
        candidatura.setHabilitado(Boolean.FALSE);
        candidaturaRepository.save(candidatura);
    }
}
