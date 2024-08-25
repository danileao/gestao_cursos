package br.com.danieleleao.gestao_cursos.modules.students;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danieleleao.gestao_cursos.modules.students.entities.EnrollmentEntity;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, UUID> {

    Optional<EnrollmentEntity> findByStudentIdAndCourseId(UUID studentId, UUID courseId);

}
