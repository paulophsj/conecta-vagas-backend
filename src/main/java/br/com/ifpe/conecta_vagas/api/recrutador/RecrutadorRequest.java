package br.com.ifpe.conecta_vagas.api.recrutador;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.ifpe.conecta_vagas.modelo.recrutador.Recrutador;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.PorteEmpresa;
import br.com.ifpe.conecta_vagas.util.enums.recrutador.Setores;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "O campo senha não pode ser 'null' ou vazio.")
    @Size(min = 6, max = 50, message = "A senha deve ter entre {min} e {max} caracteres.")
    private String senha;

    @NotBlank(message = "O campo email não pode ser 'null' ou vazio.")
    private String email;

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

    @NotBlank(message = "O campo porte da empresa não pode ser 'null' ou vazio.")
    private PorteEmpresa porteEmpresa;

    @NotBlank(message = "O campo setor da empresa não pode ser 'null' ou vazio.")
    private Setores setorEmpresa;

    @NotBlank(message = "O campo número de funcionários não pode ser 'null' ou vazio.")
    @Size(max = 10, message = "O número de funcionários deve ter no máximo {max} caracteres.")
    private Number numeroFuncionarios;

    public Recrutador build() {
        return Recrutador.builder()
                .cnpj(cnpj)
                .nomeEmpresa(nomeEmpresa)
                .senha(senha)
                .email(email)
                .numeroTelefone(numeroTelefone)
                .estado(estado)
                .cidade(cidade)
                .descricaoEmpresa(descricaoEmpresa)
                .anoFundacao(anoFundacao)
                .porteEmpresa(porteEmpresa)
                .numeroFuncionarios(numeroFuncionarios)
                .setorEmpresa(setorEmpresa)
                .build();
    }
}
