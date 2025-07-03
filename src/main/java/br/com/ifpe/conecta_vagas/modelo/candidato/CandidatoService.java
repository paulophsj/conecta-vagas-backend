package br.com.ifpe.conecta_vagas.modelo.candidato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.acesso.PerfilRepository;
import br.com.ifpe.conecta_vagas.modelo.acesso.UsuarioService;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidatoRepository;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademicaRepository;
import br.com.ifpe.conecta_vagas.util.exceptions.CandidatoException;
import jakarta.transaction.Transactional;

@Service
public class CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private EnderecoCandidatoRepository enderecoCandidatoRepository;
    @Autowired
    private FormacaoAcademicaRepository formacaoAcademicaRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    public List<Candidato> findAll() {
        return this.candidatoRepository.findAll();
    };

    public Candidato findOne(Long id) {
        return this.candidatoRepository.findById(id).get();
    };

    @Transactional
    public Candidato save(Candidato candidato) {
        if (candidato.getNome().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "nome");

        }
        usuarioService.save(candidato.getUsuario());

        for (Perfil perfil : candidato.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        candidato.setHabilitado(Boolean.TRUE);
        return candidatoRepository.save(candidato);
    };

    @Transactional
    public Candidato update(Long id, Candidato novoCandidato) {
        if (novoCandidato.getNome().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "nome");

        }
        Candidato antigoCandidato = this.findOne(id);

        antigoCandidato.setCargoPretendido(novoCandidato.getCargoPretendido());
        antigoCandidato.setCpf(novoCandidato.getCpf());
        antigoCandidato.setDataNascimento(novoCandidato.getDataNascimento());
        antigoCandidato.setNome(novoCandidato.getNome());
        antigoCandidato.setPretensaoSalarial(novoCandidato.getPretensaoSalarial());
        antigoCandidato.setResumoProfissional(novoCandidato.getResumoProfissional());
        antigoCandidato.setNumeroTelefone(novoCandidato.getNumeroTelefone());
        antigoCandidato.getUsuario().setPassword(novoCandidato.getUsuario().getPassword());

        return this.candidatoRepository.save(novoCandidato);
    }

    @Transactional
    public void delete(Long id) {
        Candidato candidato = this.findOne(id);
        candidato.setHabilitado(Boolean.FALSE);
    }

    /*
     * Services para relação Candidato > EnderecoCandidato
     */
    @Transactional
    public void deleteEndereco(Long id) {
        EnderecoCandidato enderecoCandidato = this.enderecoCandidatoRepository.findById(id).get();
        enderecoCandidato.setHabilitado(Boolean.FALSE);
        this.enderecoCandidatoRepository.save(enderecoCandidato);
    }

    @Transactional
    public EnderecoCandidato updateEndereco(Long id, EnderecoCandidato novoEnderecoCandidato) {
        if (!novoEnderecoCandidato.getEnderecoCep().matches("^\\d{5}-\\d{3}$")) {
            throw new CandidatoException(CandidatoException.FORMATO_CEP);
        }
        if (novoEnderecoCandidato.getEnderecoBairro().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "bairro");
        }
        if (novoEnderecoCandidato.getEnderecoCidade().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "cidade");
        }
        if (novoEnderecoCandidato.getEnderecoEstado().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "estado");
        }
        if (novoEnderecoCandidato.getEnderecoRua().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "logradouro");
        }

        EnderecoCandidato endereco = this.enderecoCandidatoRepository.findById(id).get();

        endereco.setEnderecoBairro(novoEnderecoCandidato.getEnderecoBairro());
        endereco.setEnderecoCidade(novoEnderecoCandidato.getEnderecoCidade());
        endereco.setEnderecoEstado(novoEnderecoCandidato.getEnderecoEstado());
        endereco.setEnderecoNumero(novoEnderecoCandidato.getEnderecoNumero());
        endereco.setEnderecoComplemento(novoEnderecoCandidato.getEnderecoComplemento());
        endereco.setEnderecoCep(novoEnderecoCandidato.getEnderecoCep());
        endereco.setEnderecoRua(novoEnderecoCandidato.getEnderecoRua());

        return this.enderecoCandidatoRepository.save(endereco);
    }

    @Transactional
    public EnderecoCandidato saveEndereco(Long id, EnderecoCandidato enderecoCandidato) {
        if (!enderecoCandidato.getEnderecoCep().matches("^\\d{5}-\\d{3}$")) {
            throw new CandidatoException(CandidatoException.FORMATO_CEP);
        }
        if (enderecoCandidato.getEnderecoBairro().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "bairro");
        }
        if (enderecoCandidato.getEnderecoCidade().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "cidade");
        }
        if (enderecoCandidato.getEnderecoEstado().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "estado");
        }
        if (enderecoCandidato.getEnderecoRua().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "logradouro");
        }

        Candidato candidato = this.findOne(id);

        enderecoCandidato.setCandidato(candidato);
        enderecoCandidato.setHabilitado(Boolean.TRUE);

        enderecoCandidatoRepository.save(enderecoCandidato);
        List<EnderecoCandidato> listaEnderecos = candidato.getEnderecos();

        if (listaEnderecos == null) {
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
    public void deleteFormacao(Long id) {
        FormacaoAcademica formacaoAcademica = this.formacaoAcademicaRepository.findById(id).get();
        formacaoAcademica.setHabilitado(Boolean.FALSE);
        this.formacaoAcademicaRepository.save(formacaoAcademica);
    }

    @Transactional
    public FormacaoAcademica updateFormacao(Long id, FormacaoAcademica novaFormacaoAcademica) {
        if (novaFormacaoAcademica.getCurso().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "curso");
        }
        if (novaFormacaoAcademica.getInstituicao().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "instituição");
        }
        FormacaoAcademica formacaoExistente = this.formacaoAcademicaRepository.findById(id).get();

        formacaoExistente.setAnoConclusao(novaFormacaoAcademica.getAnoConclusao());
        formacaoExistente.setInstituicao(novaFormacaoAcademica.getInstituicao());
        formacaoExistente.setCurso(novaFormacaoAcademica.getCurso());
        formacaoExistente.setAnoConclusao(novaFormacaoAcademica.getAnoConclusao());

        return this.formacaoAcademicaRepository.save(formacaoExistente);
    }

    @Transactional
    public FormacaoAcademica saveFormacao(Long id, FormacaoAcademica formacaoAcademica) {
        if (formacaoAcademica.getCurso().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "curso");
        }
        if (formacaoAcademica.getInstituicao().matches(".*\\d.*")) {
            throw new CandidatoException(CandidatoException.APENAS_LETRAS, "instituição");
        }
        Candidato candidato = this.findOne(id);

        formacaoAcademica.setCandidato(candidato);
        formacaoAcademica.setHabilitado(Boolean.TRUE);

        formacaoAcademicaRepository.save(formacaoAcademica);
        List<FormacaoAcademica> listaFormacao = candidato.getFormacaoAcademica();

        if (listaFormacao == null) {
            listaFormacao = new ArrayList<FormacaoAcademica>();
        }

        listaFormacao.add(formacaoAcademica);

        candidato.setFormacaoAcademica(listaFormacao);
        candidatoRepository.save(candidato);

        return formacaoAcademica;
    }
}