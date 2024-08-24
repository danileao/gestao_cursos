package br.com.danieleleao.gestao_cursos.modules.courses.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateCourseRequest {

    private String title;
    private String description;
    private UUID instructorId;

}
