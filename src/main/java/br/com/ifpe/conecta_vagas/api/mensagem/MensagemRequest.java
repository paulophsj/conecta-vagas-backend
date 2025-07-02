package br.com.ifpe.conecta_vagas.api.mensagem;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequest {
    @NotNull
    private Long idChat;

    @NotBlank(message = "O campo conteudo não pode ser nulo ou vazio")
    @Length(max = 1000, message = "O campo conteudo deve ter no máximo {max} caracteres.")
    private String conteudo;
}
