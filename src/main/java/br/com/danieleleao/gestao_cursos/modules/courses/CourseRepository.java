package br.com.danieleleao.gestao_cursos.modules.courses;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danieleleao.gestao_cursos.modules.courses.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
