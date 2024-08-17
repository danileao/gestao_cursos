package br.com.danieleleao.gestao_cursos.modules.users.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danieleleao.gestao_cursos.exceptions.ValidationException;
import br.com.danieleleao.gestao_cursos.modules.users.UserRepository;
import br.com.danieleleao.gestao_cursos.modules.users.dto.CreateUserRequest;
import br.com.danieleleao.gestao_cursos.modules.users.entities.UserEntity;
import br.com.danieleleao.gestao_cursos.utils.EmailValidator;
import br.com.danieleleao.gestao_cursos.utils.PasswordValidator;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // SOLID 
    // Single Responsability Principle 
    public String execute(CreateUserRequest user) {

        // Dados obrigatórios 
        if (user.getName()
                == null || user.getRole() == null
                || user.getPassword() == null || user.getEmail() == null) {
            throw new ValidationException("Preencher campos obrigatórios!");
        }

        // Verificar se e-mail é valido 
        if (!EmailValidator.isValid(user.getEmail())) {
            throw new ValidationException("Email inválido!");
        }

        // Verificar se senha é segura
        if (!PasswordValidator.isPasswordSecure(user.getPassword())) {
            throw new ValidationException("Senha não atende aos critérios!");
        }

        // Verificar se email está cadastrado
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(user.getEmail());

        if (userEntity.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        var passwordCrypt = passwordEncoder.encode(user.getPassword());

        // Salvar o usuário
        UserEntity userToSave = UserEntity.builder()
                .email(user.getEmail())
                .password(passwordCrypt)
                .name(user.getName())
                .role(user.getRole()).build();

        userToSave = this.userRepository.save(userToSave);

        // Enviar e-mail de confirmação
        // Retornar mensagem de sucesso
        return "linkDoUsuarioCriado " + userToSave.getId();
    }

}
