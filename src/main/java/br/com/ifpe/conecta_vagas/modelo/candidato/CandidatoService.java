package br.com.ifpe.conecta_vagas.modelo.candidato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.acesso.PerfilRepository;
import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;
import br.com.ifpe.conecta_vagas.modelo.acesso.UsuarioService;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidatoRepository;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademicaRepository;
import br.com.ifpe.conecta_vagas.modelo.seguranca.JwtService;
import br.com.ifpe.conecta_vagas.util.exceptions.CandidatoException;
import jakarta.servlet.http.HttpServletRequest;
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
    private JwtService jwtService;

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
        if(candidatoRepository.existsByCpf(candidato.getCpf())){
            throw new CandidatoException(CandidatoException.CPF_REPETIDO);
        }
        if(candidatoRepository.existsByNumeroTelefone(candidato.getNumeroTelefone())){
            throw new CandidatoException(CandidatoException.TELEFONE_REPETIDO);
        }
        if(candidatoRepository.existsByUsuario_Username(candidato.getUsuario().getUsername())){
            throw new CandidatoException(CandidatoException.EMAIL_REPETIDO);
        }

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
        Candidato antigoCandidato = this.findOne(id);


        antigoCandidato.setCargoPretendido(novoCandidato.getCargoPretendido());
        antigoCandidato.setCpf(novoCandidato.getCpf());
        antigoCandidato.setDataNascimento(novoCandidato.getDataNascimento());
        antigoCandidato.setNome(novoCandidato.getNome());
        antigoCandidato.setPretensaoSalarial(novoCandidato.getPretensaoSalarial());
        antigoCandidato.setResumoProfissional(novoCandidato.getResumoProfissional());
        antigoCandidato.setNumeroTelefone(novoCandidato.getNumeroTelefone());

        antigoCandidato.setUsuario(antigoCandidato.getUsuario());

        return candidatoRepository.save(antigoCandidato);
    }

    @Transactional
    public void delete(Long id) {
        Candidato candidato = this.findOne(id);
        candidato.setHabilitado(Boolean.FALSE);
    }

    /*
     * Services para relação Candidato > EnderecoCandidato
     */
    public EnderecoCandidato findOneEndereco(Long id){
        return enderecoCandidatoRepository.findById(id).orElseThrow(() -> new CandidatoException(CandidatoException.ENDERECO_NAO_ENCONTRADO));
    }
    public List<EnderecoCandidato> findAllEndereco(Long id) {
        List<EnderecoCandidato> allEnderecos = this.enderecoCandidatoRepository.findAllByIdCandidato(id);
        return allEnderecos;
    }

    @Transactional
    public void deleteEndereco(Long id) {
        EnderecoCandidato enderecoCandidato = this.enderecoCandidatoRepository.findById(id).get();
        enderecoCandidato.setHabilitado(Boolean.FALSE);
        this.enderecoCandidatoRepository.save(enderecoCandidato);
    }

    @Transactional
    public EnderecoCandidato updateEndereco(Long id, EnderecoCandidato novoEnderecoCandidato) {
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
    public FormacaoAcademica findOneFormacao(Long id){
        return formacaoAcademicaRepository.findById(id).orElseThrow(() -> new CandidatoException(CandidatoException.FORMACAO_NAO_ENCONTRADA));
    }
    public List<FormacaoAcademica> findAllFormacao(Long id) {
        List<FormacaoAcademica> allFormacao = this.formacaoAcademicaRepository.findAllByIdCandidato(id);
        return allFormacao;
    }

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
        formacaoExistente.setNivelAcademico(novaFormacaoAcademica.getNivelAcademico());

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
        public Candidato obterCandidatoLogado(HttpServletRequest request) {

        Candidato candidatoLogado = null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);

            Usuario usuarioEncontrado = usuarioService.findByUsername(userEmail);
            candidatoLogado = this.candidatoRepository.findByUsuario(usuarioEncontrado);
            return candidatoLogado;
        }

        return candidatoLogado;
    }

}