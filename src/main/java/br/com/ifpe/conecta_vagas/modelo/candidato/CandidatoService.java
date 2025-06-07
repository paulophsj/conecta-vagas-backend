package br.com.ifpe.conecta_vagas.modelo.candidato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidatoRepository;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademicaRepository;
import jakarta.transaction.Transactional;

@Service
public class CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private EnderecoCandidatoRepository enderecoCandidatoRepository;
    @Autowired
    private FormacaoAcademicaRepository formacaoAcademicaRepository;

    //FindAll
    public List<Candidato> findAll(){
        return this.candidatoRepository.findAll();
    };
    //FindOne
    public Candidato findOne(Long id){
        return this.candidatoRepository.findById(id).get();
    };
    //Save
    @Transactional
    public Candidato save(Candidato candidato){
        candidato.setHabilitado(Boolean.TRUE);
        return candidatoRepository.save(candidato);
    };
    //Update
    @Transactional
    public Candidato update(Long id, Candidato novoCandidato){
        Candidato antigoCandidato = this.findOne(id);

        antigoCandidato.setCargoPretendido(novoCandidato.getCargoPretendido());
        antigoCandidato.setCpf(novoCandidato.getCpf());
        antigoCandidato.setDataNascimento(novoCandidato.getDataNascimento());
        antigoCandidato.setNome(novoCandidato.getNome());   
        antigoCandidato.setPretensaoSalarial(novoCandidato.getPretensaoSalarial());
        antigoCandidato.setResumoProfissional(novoCandidato.getResumoProfissional());
        antigoCandidato.setNumeroTelefone(novoCandidato.getNumeroTelefone());

        return this.candidatoRepository.save(novoCandidato);
    }
    //Delete
    @Transactional
    public void delete(Long id){
        Candidato candidato = this.findOne(id);
        candidato.setHabilitado(Boolean.FALSE);
    }
    
    /*
     * Services para relação Candidato > EnderecoCandidato
     */
    @Transactional
    public EnderecoCandidato saveEndereco(Long id, EnderecoCandidato enderecoCandidato){
        Candidato candidato = this.findOne(id);

        enderecoCandidato.setCandidato(candidato);
        enderecoCandidato.setHabilitado(Boolean.TRUE);

        enderecoCandidatoRepository.save(enderecoCandidato);
        List<EnderecoCandidato> listaEnderecos = candidato.getEnderecos();

        if(listaEnderecos == null){
            listaEnderecos = new ArrayList<EnderecoCandidato>();
        }
        listaEnderecos.add(enderecoCandidato);

        candidato.setEnderecos(listaEnderecos);
        candidatoRepository.save(candidato);

        return enderecoCandidato;

    }
    /*
     * Services para relação Candidato > FormacaoAcademica
     */
    @Transactional
    public FormacaoAcademica saveFormacao(Long id, FormacaoAcademica formacaoAcademica){
        Candidato candidato = this.findOne(id);

        formacaoAcademica.setCandidato(candidato);
        formacaoAcademica.setHabilitado(Boolean.TRUE);

        formacaoAcademicaRepository.save(formacaoAcademica);
        List<FormacaoAcademica> listaFormacao = candidato.getFormacaoAcademica();

        if(listaFormacao == null){
            listaFormacao = new ArrayList<FormacaoAcademica>();
        }

        listaFormacao.add(formacaoAcademica);

        candidato.setFormacaoAcademica(listaFormacao);
        candidatoRepository.save(candidato);

        return formacaoAcademica;
    }
}
