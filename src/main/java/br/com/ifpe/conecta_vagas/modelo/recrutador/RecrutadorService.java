package br.com.ifpe.conecta_vagas.modelo.recrutador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.acesso.PerfilRepository;
import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;
import br.com.ifpe.conecta_vagas.modelo.acesso.UsuarioService;
import br.com.ifpe.conecta_vagas.modelo.seguranca.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class RecrutadorService {
    @Autowired
    private RecrutadorRepository recrutadorRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    public Recrutador findOne(Long id) {
        return recrutadorRepository.findById(id).get();
    }

    public Recrutador obterRecrutadorLogado(HttpServletRequest request) {

        Recrutador recrutadorLogado = null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);

            Usuario usuarioEncontrado = usuarioService.findByUsername(userEmail);
            recrutadorLogado = this.recrutadorRepository.findByUsuario(usuarioEncontrado);
            return recrutadorLogado;
        }

        return recrutadorLogado;
    }

    @Transactional
    public Recrutador save(Recrutador recrutador) {
        recrutador.setHabilitado(Boolean.TRUE);
        usuarioService.save(recrutador.getUsuario());

        for (Perfil perfil : recrutador.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        return recrutadorRepository.save(recrutador);
    }

    @Transactional
    public Recrutador update(Long id, Recrutador novoRecrutador) {
        Recrutador antigoRecrutador = this.findOne(id);

        antigoRecrutador.setCnpj(novoRecrutador.getCnpj());
        antigoRecrutador.setNomeEmpresa(novoRecrutador.getNomeEmpresa());
        antigoRecrutador.setNumeroTelefone(novoRecrutador.getNumeroTelefone());
        antigoRecrutador.setEstado(novoRecrutador.getEstado());
        antigoRecrutador.setCidade(novoRecrutador.getCidade());
        antigoRecrutador.setDescricaoEmpresa(novoRecrutador.getDescricaoEmpresa());
        antigoRecrutador.setAnoFundacao(novoRecrutador.getAnoFundacao());
        antigoRecrutador.setPorteEmpresa(novoRecrutador.getPorteEmpresa());
        antigoRecrutador.setNumeroFuncionarios(novoRecrutador.getNumeroFuncionarios());
        antigoRecrutador.setSetorEmpresa(novoRecrutador.getSetorEmpresa());

        antigoRecrutador.setUsuario(antigoRecrutador.getUsuario());

        antigoRecrutador.getVagas().forEach(vaga -> vaga.setNomeEmpresa(novoRecrutador.getNomeEmpresa()));

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
