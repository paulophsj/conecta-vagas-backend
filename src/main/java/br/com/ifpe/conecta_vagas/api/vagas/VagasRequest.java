package br.com.ifpe.conecta_vagas.api.vagas;


import br.com.ifpe.conecta_vagas.modelo.vagas.Vagas;
import br.com.ifpe.conecta_vagas.util.enums.vagas.TipoContrato;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VagasRequest {
    
    @NotBlank(message = "O campo título não pode ser nulo ou vazio")
    private String titulo;

    private String descricao;

    @Size(max = 1000, message = "O campo requisitos pode ter no máximo {max} caracteres.")
    @NotEmpty(message = "O campo requisitos não pode ser vazio")
    private String requisitos;

    @Size(max = 600, message = "O campo localizacao pode ter no máximo {max} caracteres.")
    private String localizacao;

    private Double salario;

    private Boolean ativa;
    
    @NotNull(message = "O campo tipoContrato não pode ser nulo ou vazio")
    private TipoContrato tipoContrato;

    @Max(value = 999, message = "O campo cargaHoraria pode ter no máximo {value}.")
    private Integer cargaHoraria;

    public Vagas build(){
        return Vagas.builder()
                    .ativa(ativa)
                    .cargaHoraria(cargaHoraria)
                    .descricao(descricao)
                    .localizacao(localizacao)
                    .tipoContrato(tipoContrato)
                    .salario(salario)
                    .titulo(titulo)
                    .requisitos(requisitos)
                    .build();
    }
}
