package br.com.danieleleao.gestao_cursos.modules.courses.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danieleleao.gestao_cursos.modules.courses.entities.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {

    Optional<ModuleEntity> findByCourseIdAndOrder(UUID courseID, Integer order);

}
