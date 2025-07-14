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
public class CandidatoUpdateRequest {
    @CPF(message = "O campo CPF deve ser no formado 123.456.789-01 e ser um CPF válido")
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
    private Double pretensaoSalarial;

    @Size(max = 500, message = "O resumo profissional deve ter no máximo 500 caracteres.")
    private String resumoProfissional;

    @Size(min = 15, max = 15, message = "O número de telefone deve ter exatamente {max} caracteres.")
    private String numeroTelefone;

    public Candidato build() {
        return Candidato.builder()
                .cpf(cpf)
                .nome(nome)
                .dataNascimento(dataNascimento)
                .cargoPretendido(cargoPretendido)
                .pretensaoSalarial(pretensaoSalarial)
                .resumoProfissional(resumoProfissional)
                .numeroTelefone(numeroTelefone)
                .build();
    }
}
