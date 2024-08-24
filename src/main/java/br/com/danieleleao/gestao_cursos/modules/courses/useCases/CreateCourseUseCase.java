package br.com.danieleleao.gestao_cursos.modules.courses.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.exceptions.ValidationException;
import br.com.danieleleao.gestao_cursos.modules.courses.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateCourseRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.danieleleao.gestao_cursos.modules.users.UserRepository;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public String execute(CreateCourseRequest createCourseRequest) throws Exception {

        if (!userRepository.existsById(createCourseRequest.getInstructorId())) {
            throw new Exception("Professor(a) não encontrado");
        }

        if (createCourseRequest.getDescription() == null || createCourseRequest.getTitle() == null) {
            throw new ValidationException("Nome e título são obrigatórios");
        }

        CourseEntity courseEntity = CourseEntity.builder()
                .description(createCourseRequest.getDescription())
                .title(createCourseRequest.getTitle())
                .instructorId(createCourseRequest.getInstructorId())
                .build();

        courseEntity = this.courseRepository.save(courseEntity);

        return courseEntity.getId().toString();
    }

}
