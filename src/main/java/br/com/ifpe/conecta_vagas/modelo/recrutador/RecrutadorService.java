package br.com.ifpe.conecta_vagas.modelo.recrutador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.acesso.PerfilRepository;
import br.com.ifpe.conecta_vagas.modelo.acesso.UsuarioService;
import jakarta.transaction.Transactional;

@Service
public class RecrutadorService {
    @Autowired
    private RecrutadorRepository recrutadorRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    public Recrutador findOne(Long id) {
        return recrutadorRepository.findById(id).get();
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
        antigoRecrutador.getUsuario().setPassword(novoRecrutador.getUsuario().getPassword());
        antigoRecrutador.getUsuario().setUsername(novoRecrutador.getUsuario().getUsername());
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
