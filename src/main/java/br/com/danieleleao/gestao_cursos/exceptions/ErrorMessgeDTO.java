package br.com.danieleleao.gestao_cursos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessgeDTO {

    private String message;
    private String field;

}
