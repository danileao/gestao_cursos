package br.com.danieleleao.gestao_cursos.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_cursos.modules.auth.dto.CreateAuthRequest;
import br.com.danieleleao.gestao_cursos.modules.auth.useCases.CreateAuthUseCase;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CreateAuthUseCase createAuthUseCase;

    @PostMapping("/instructor")
    public ResponseEntity<?> authInstructor(@RequestBody CreateAuthRequest createAuthRequest) {
        try {
            var token = this.createAuthUseCase.execute(createAuthRequest);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
