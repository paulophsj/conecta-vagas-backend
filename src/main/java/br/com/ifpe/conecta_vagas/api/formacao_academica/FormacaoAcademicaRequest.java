package br.com.ifpe.conecta_vagas.api.formacao_academica;

import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.util.enums.NivelAcademico;
import jakarta.validation.constraints.NotBlank;
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
public class FormacaoAcademicaRequest {
    private NivelAcademico nivelAcademico;

    @NotBlank(message = "O campo 'Curso' é obrigatório")
    private String curso;

    @NotBlank(message = "O campo 'Instituicao' é obrigatório")
    private String instituicao;

    private Integer anoConclusao;
    
    public FormacaoAcademica build(){
        
        return FormacaoAcademica.builder()
            .nivelAcademico(nivelAcademico)
            .curso(curso)
            .instituicao(instituicao)
            .anoConclusao(anoConclusao)
            .build();
    }
}
