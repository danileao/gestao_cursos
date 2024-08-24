package br.com.danieleleao.gestao_cursos.modules.auth.dto;

import lombok.Data;

@Data
public class CreateAuthRequest {

    private String password;
    private String email;

}
