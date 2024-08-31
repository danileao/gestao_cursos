package br.com.danieleleao.gestao_cursos.modules.courses.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.exceptions.ForbiddenException;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateModuleRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.entities.ModuleEntity;
import br.com.danieleleao.gestao_cursos.modules.courses.repositories.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.courses.repositories.ModuleRepository;

@Service
public class CreateModuleUseCase {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public UUID execute(CreateModuleRequest createModuleRequest, UUID instructorId) throws Exception {
        var course = this.courseRepository.findById(createModuleRequest.getCourseId());

        if (course.isEmpty()) {
            throw new Exception("Curso não encontrado!");
        }

        if (!course.get().getInstructorId().equals(instructorId)) {
            throw new ForbiddenException();

        }

        // SALVAR
        var orderExists = this.moduleRepository.findByCourseIdAndOrder(createModuleRequest.getCourseId(),
                createModuleRequest.getOrder());

        if (orderExists.isPresent()) {
            throw new Exception("Order já cadastrada para um outro módulo");
        }

        var titleExists = this.moduleRepository.findByCourseIdAndTitle(createModuleRequest.getCourseId(), createModuleRequest.getTitle());

        if (titleExists.isPresent()) {
            throw new Exception("Título já existente para esse curso!");
        }
 
        
        var moduleEntity = ModuleEntity.builder()
                .title(createModuleRequest.getTitle())
                .courseId(createModuleRequest.getCourseId())
                .description(createModuleRequest.getDescription())
                .order(createModuleRequest.getOrder())
                .build();

        moduleEntity = this.moduleRepository.save(moduleEntity);

        return moduleEntity.getId();

    }

}
