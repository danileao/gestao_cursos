package br.com.danieleleao.gestao_cursos.modules.users.dto;

import br.com.danieleleao.gestao_cursos.modules.users.entities.RoleUser;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String email;
    private String password;
    private String name;
    private RoleUser role;

}
