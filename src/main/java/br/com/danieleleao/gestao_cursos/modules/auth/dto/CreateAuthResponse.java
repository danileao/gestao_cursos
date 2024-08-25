package br.com.danieleleao.gestao_cursos.modules.auth.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAuthResponse {

    private String acess_token;
    private Date expires_in;
    private Date created_at;

}
