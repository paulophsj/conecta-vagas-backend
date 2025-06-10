package br.com.ifpe.conecta_vagas.api.endereco_candidato;

import br.com.ifpe.conecta_vagas.modelo.endereco_candidato.EnderecoCandidato;
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
    private String enderecoCep;

    private String enderecoRua;

    private Integer enderecoNumero;

    private String enderecoComplemento;

    private String enderecoBairro;

    private String enderecoCidade;
    
    private String enderecoEstado;

    public EnderecoCandidato build(){
        return EnderecoCandidato.builder()
            .enderecoBairro(enderecoBairro)
            .enderecoCep(enderecoCep)
            .enderecoCidade(enderecoCidade)
            .enderecoComplemento(enderecoComplemento)
            .enderecoEstado(enderecoEstado)
            .enderecoNumero(enderecoNumero)
            .enderecoRua(enderecoRua)
            .build();
            
    }
}
