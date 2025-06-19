package br.com.ifpe.conecta_vagas.modelo.recrutador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RecrutadorService {
    @Autowired
    private RecrutadorRepository recrutadorRepository;

    
    public Recrutador findOne(Long id) {
        return recrutadorRepository.findById(id).get();
    }

    @Transactional
    public Recrutador save(Recrutador recrutador) {
        recrutador.setHabilitado(Boolean.TRUE);
        return recrutadorRepository.save(recrutador);
    }
    
    @Transactional
    public Recrutador update(Long id, Recrutador novoRecrutador) {
        Recrutador antigoRecrutador = this.findOne(id);

        antigoRecrutador.setCnpj(novoRecrutador.getCnpj());
        antigoRecrutador.setNomeEmpresa(novoRecrutador.getNomeEmpresa());
        antigoRecrutador.setSenha(novoRecrutador.getSenha());
        antigoRecrutador.setEmail(novoRecrutador.getEmail());
        antigoRecrutador.setNumeroTelefone(novoRecrutador.getNumeroTelefone());
        antigoRecrutador.setEstado(novoRecrutador.getEstado());
        antigoRecrutador.setCidade(novoRecrutador.getCidade());
        antigoRecrutador.setDescricaoEmpresa(novoRecrutador.getDescricaoEmpresa());
        antigoRecrutador.setAnoFundacao(novoRecrutador.getAnoFundacao());
        antigoRecrutador.setPorteEmpresa(novoRecrutador.getPorteEmpresa());
        antigoRecrutador.setNumeroFuncionarios(novoRecrutador.getNumeroFuncionarios());
        antigoRecrutador.setSetorEmpresa(novoRecrutador.getSetorEmpresa());

        return recrutadorRepository.save(antigoRecrutador);
    }

    @Transactional
    public void delete(Long id) {
        Recrutador recrutador = this.findOne(id);
        if (recrutador != null) {
            recrutador.setHabilitado(Boolean.FALSE);
            recrutadorRepository.save(recrutador);
        }
    }
}
