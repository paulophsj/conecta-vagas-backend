package br.com.ifpe.conecta_vagas.api.candidato;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.conecta_vagas.modelo.candidato.Candidato;
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
    private String cpf;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String cargoPretendido;

    private double pretensaoSalarial;

    private String resumoProfissional;

    private String numeroTelefone;

    public Candidato build(){
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
