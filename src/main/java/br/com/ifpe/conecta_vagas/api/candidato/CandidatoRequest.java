package br.com.ifpe.conecta_vagas.api.candidato;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
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
public class CandidatoRequest {
    @CPF(message = "O campo CPF deve ser no formado 123.456.789-01")
    @NotBlank(message = "O campo CPF não pode ser 'null' ou vazio.")
    private String cpf;

    @NotBlank(message = "O campo nome não pode ser 'null' ou vazio.")
    private String nome;

    @Past(message = "A data de nascimento deve estar no passado.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Size(max = 100, message = "O cargo pretendido deve ter no máximo 100 caracteres.")
    private String cargoPretendido;

    @PositiveOrZero(message = "A pretensão salarial não pode ser negativa.")
    private double pretensaoSalarial;

    @Size(max = 500, message = "O resumo profissional deve ter no máximo 500 caracteres.")
    private String resumoProfissional;

    private String numeroTelefone;

    @NotBlank(message = "O campo senha não pode ser 'null' ou vazio.")
    @Size(min = 6, max = 15, message = "A senha deve ter entre {min} e {max} caracteres.")
    private String senha;

    public Candidato build(){
        return Candidato.builder()
            .cpf(cpf)
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cargoPretendido(cargoPretendido)
            .pretensaoSalarial(pretensaoSalarial)
            .resumoProfissional(resumoProfissional)
            .numeroTelefone(numeroTelefone)
            .senha(senha)
            .build();
    }
}
