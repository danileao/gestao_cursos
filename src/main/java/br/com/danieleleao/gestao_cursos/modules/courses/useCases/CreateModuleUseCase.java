package br.com.danieleleao.gestao_cursos.modules.courses.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.modules.courses.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateModuleRequest;

@Service
public class CreateModuleUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(CreateModuleRequest createModuleRequest, UUID instructorId) throws Exception {
        var course = this.courseRepository.findById(createModuleRequest.getCourseId());

        if (course.isEmpty()) {
            throw new Exception("Curso não encontrado!");
        }

        if (!course.get().getInstructorId().equals(instructorId)) {
            throw new Exception("Curso não pertencente a esse instrutor");
        }
        

        // SALVAR
    }

}
