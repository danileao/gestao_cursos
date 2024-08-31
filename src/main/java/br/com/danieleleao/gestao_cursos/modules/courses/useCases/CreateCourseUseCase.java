package br.com.danieleleao.gestao_cursos.modules.courses.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.exceptions.ValidationException;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateCourseRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.danieleleao.gestao_cursos.modules.courses.repositories.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.users.UserRepository;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public String execute(CreateCourseRequest createCourseRequest, String instructorId) throws Exception {

        if (!userRepository.existsById(UUID.fromString(instructorId))) {
            throw new Exception("Professor(a) não encontrado");
        }

        if (createCourseRequest.getDescription() == null || createCourseRequest.getTitle() == null) {
            throw new ValidationException("Nome e título são obrigatórios");
        }

        CourseEntity courseEntity = CourseEntity.builder()
                .description(createCourseRequest.getDescription())
                .title(createCourseRequest.getTitle())
                .instructorId(UUID.fromString(instructorId))
                .build();

        courseEntity = this.courseRepository.save(courseEntity);

        return courseEntity.getId().toString();
    }

}
