package br.com.ifpe.conecta_vagas.modelo.vagas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import jakarta.transaction.Transactional;

@Service
public class VagasService {
    @Autowired
    private VagasRepository vagasRepository;
    @Autowired
    private RecrutadorService recrutadorService;

    public Vagas findOne(Long id){
        return this.vagasRepository.findById(id).get();
    }
    public List<Vagas> findAllVagas(Long id){
        List<Vagas> allVagas = this.vagasRepository.findAllVagas(id);
        return allVagas;
    }
    @Transactional
    public Vagas save(Long id, Vagas vaga){
        Recrutador recrutador = this.recrutadorService.findOne(id);

        vaga.setRecrutador(recrutador);
        vaga.setHabilitado(Boolean.TRUE);

        vagasRepository.save(vaga);
        List<Vagas> listVagas = recrutador.getVagas();

        if(listVagas == null){
            listVagas = new ArrayList<Vagas>();
        }

        listVagas.add(vaga);

        recrutador.setVagas(listVagas);
        this.vagasRepository.save(vaga);

        return vaga;
    }
    @Transactional
    public Vagas update(Long id, Vagas novaVaga){
        Vagas antigaVaga = this.findOne(id);

        antigaVaga.setAtiva(novaVaga.getAtiva());
        antigaVaga.setCargaHoraria(novaVaga.getCargaHoraria());
        antigaVaga.setDescricao(novaVaga.getDescricao());
        antigaVaga.setLocalizacao(novaVaga.getLocalizacao());
        antigaVaga.setRequisitos(novaVaga.getRequisitos());
        antigaVaga.setSalario(novaVaga.getSalario());
        antigaVaga.setTipoContrato(novaVaga.getTipoContrato());
        antigaVaga.setTitulo(novaVaga.getTitulo());
        antigaVaga.setAtiva(novaVaga.getAtiva());

        return this.vagasRepository.save(antigaVaga);
    }
    @Transactional
    public void remove(Long id){
        Vagas antigaVaga = this.findOne(id);
        if(antigaVaga != null){
            antigaVaga.setHabilitado(Boolean.FALSE);
            this.vagasRepository.save(antigaVaga);
        }
    }
}
