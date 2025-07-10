package br.com.ifpe.conecta_vagas.modelo.vagas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.modelo.recrutador.RecrutadorService;
import br.com.ifpe.conecta_vagas.util.exceptions.VagaException;
import jakarta.transaction.Transactional;

@Service
public class VagasService {
    @Autowired
    private VagasRepository vagasRepository;
    @Autowired
    private RecrutadorService recrutadorService;

    public Vagas findOne(Long id){
        Vagas vaga = this.vagasRepository.findById(id).orElseThrow(() -> new VagaException(VagaException.VAGA_NAO_ENCONTRADA));
        return vaga;
    }

    //Obter todas as vagas do banco de dados
    public List<Vagas> findAllVagas(){
        return vagasRepository.findAll();
    }
    //Obter todas as vagas de um recrutador
    public List<Vagas> findAllVagasByRecrutador(Long id){
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
        antigaVaga.setFormato(novaVaga.getFormato());
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
