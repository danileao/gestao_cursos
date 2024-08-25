package br.com.danieleleao.gestao_cursos.modules.students.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.modules.courses.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.students.EnrollmentRepository;
import br.com.danieleleao.gestao_cursos.modules.students.entities.EnrollmentEntity;
import br.com.danieleleao.gestao_cursos.modules.users.UserRepository;

@Service
public class CreateEnrollmentUseCase {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void execute(UUID courseId, UUID studentId) throws Exception {
        // Validar student
        var student = this.userRepository.findById(studentId);

        if (student == null) {
            throw new Exception("Aluno incorreto");
        }
        // Validar curso

        var course = this.courseRepository.findById(courseId);
        if (course == null) {
            throw new Exception("Curso inválido");

        }

        // Validar se estudante NAO tem uma inscrição para esse mesmo curso
        var enrollment = this.enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment.isPresent()) {
            throw new Exception("Já existe uma inscrição para esse curso");
        }

        var enrollmentEntity = EnrollmentEntity.builder().courseId(courseId).studentId(studentId).build();
        this.enrollmentRepository.save(enrollmentEntity);
    }

}
