package br.com.ifpe.conecta_vagas.api.recrutador;

import java.util.Arrays;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.ifpe.conecta_vagas.modelo.acesso.Perfil;
import br.com.ifpe.conecta_vagas.modelo.acesso.Usuario;
import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.PorteEmpresa;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.Setores;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecrutadorRequest {
    @CNPJ(message = "CNPJ inválido")
    @NotBlank(message = "O campo CNPJ não pode ser 'null' ou vazio.")
    private String cnpj;

    private String nomeEmpresa;

    @Size(min = 15, max = 15, message = "O número de telefone deve ter exatamente {max} caracteres.")
    @NotBlank(message = "O campo número de telefone não pode ser 'null' ou vazio.")
    private String numeroTelefone;

    @NotBlank(message = "O campo sigla do estado não pode ser 'null' ou vazio.")
    @Size(min = 2, max = 2, message = "A sigla do estado deve ter exatamente {max} caracteres.")
    private String estado;

    @NotBlank(message = "O campo cidade não pode ser 'null' ou vazio.")
    @Size(max = 100, message = "A cidade deve ter no máximo {max} caracteres.")
    private String cidade;

    @Size(max = 500, message = "A descrição da empresa deve ter no máximo {max} caracteres.")
    private String descricaoEmpresa;

    @Size(min = 4, max = 4, message = "O ano de fundação deve ter exatamente {max} caracteres.")
    @NotBlank(message = "O campo ano de fundação não pode ser 'null' ou vazio.")
    private String anoFundacao;

    @NotNull(message = "O campo porte da empresa não pode ser 'null' ou vazio.")
    private PorteEmpresa porteEmpresa;

    @NotNull(message = "O campo setor da empresa não pode ser 'null' ou vazio.")
    private Setores setorEmpresa;

    @Max(value = 10000, message = "O número de funcionários deve ter no máximo {value} caracteres.")
    private Number numeroFuncionarios;

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    @Size(min = 6, max = 50, message = "A senha deve ter entre {min} e {max} caracteres.")
    private String password;

    public Usuario buildRecrutador() {
        return Usuario.builder()
                .username(email)
                .password(password)
                .roles(Arrays.asList(new Perfil(Perfil.ROLE_RECRUTADOR)))
                .build();
    }

    public Recrutador build() {
        return Recrutador.builder()
                .cnpj(cnpj)
                .nomeEmpresa(nomeEmpresa)
                .numeroTelefone(numeroTelefone)
                .estado(estado)
                .cidade(cidade)
                .descricaoEmpresa(descricaoEmpresa)
                .anoFundacao(anoFundacao)
                .porteEmpresa(porteEmpresa)
                .numeroFuncionarios(numeroFuncionarios)
                .setorEmpresa(setorEmpresa)
                .usuario(buildRecrutador())
                .build();
    }
}
