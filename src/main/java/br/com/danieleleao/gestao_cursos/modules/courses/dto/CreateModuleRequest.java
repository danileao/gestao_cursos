package br.com.danieleleao.gestao_cursos.modules.courses.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateModuleRequest {

    @NotBlank
    @Length(min = 10, max = 300)

    private String title;

    @NotBlank
    @Length(min = 10, max = 300)
    private String description;

    @NotNull
    private Integer order;

    @NotNull
    private UUID courseId;

}
