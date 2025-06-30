package br.com.ifpe.conecta_vagas.modelo.vagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class VagasService {
    @Autowired
    private VagasRepository vagasRepository;

    public Vagas findOne(Long id){
        return this.vagasRepository.findById(id).get();
    }
    @Transactional
    public Vagas save(Vagas vaga){
        vaga.setHabilitado(Boolean.TRUE);
        return this.vagasRepository.save(vaga);
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

        return this.save(antigaVaga);
    }
    @Transactional
    public void remove(Long id){
        Vagas antigaVaga = this.findOne(id);
        if(antigaVaga != null){
            antigaVaga.setHabilitado(Boolean.FALSE);
            this.save(antigaVaga);
        }
    }
}
