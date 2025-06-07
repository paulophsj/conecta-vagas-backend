package br.com.ifpe.conecta_vagas.api.formacao_academica;

import java.time.LocalDate;

import br.com.ifpe.conecta_vagas.modelo.formacao_academica.FormacaoAcademica;
import br.com.ifpe.conecta_vagas.util.enums.NivelAcademico;
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

    private String curso;

    private String instituicao;

    private LocalDate anoConclusao;
    
    public FormacaoAcademica build(){
        
        return FormacaoAcademica.builder()
            .nivelAcademico(nivelAcademico)
            .curso(curso)
            .instituicao(instituicao)
            .anoConclusao(anoConclusao)
            .build();
    }
}
