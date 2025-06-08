package br.com.ifpe.conecta_vagas.api.endereco_candidato;

import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoCandidatoRequest {
    private Integer enderecoNumero;

    @NotBlank(message = "O campo 'Endereco Rua' é obrigatório")
    private String enderecoRua;

    @NotBlank(message = "O campo 'Endereco Bairro' é obrigatório")
    private String enderecoBairro;

    @NotBlank(message = "O campo 'Endereco Cidade' é obrigatório")
    private String enderecoCidade;

    @NotBlank(message = "O campo 'Endereco Estado' é obrigatório")
    private String enderecoEstado;

    public EnderecoCandidato build(){
        return EnderecoCandidato.builder()
            .enderecoBairro(enderecoBairro)
            .enderecoCidade(enderecoCidade)
            .enderecoEstado(enderecoEstado)
            .enderecoRua(enderecoRua)
            .enderecoNumero(enderecoNumero)
            .build();
            
    }
}
