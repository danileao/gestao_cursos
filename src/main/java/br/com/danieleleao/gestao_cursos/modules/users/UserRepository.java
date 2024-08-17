package br.com.danieleleao.gestao_cursos.modules.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danieleleao.gestao_cursos.modules.users.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

}
