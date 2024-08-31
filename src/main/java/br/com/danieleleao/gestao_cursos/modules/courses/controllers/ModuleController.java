package br.com.danieleleao.gestao_cursos.modules.courses.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_cursos.exceptions.ForbiddenException;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateModuleRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.useCases.CreateModuleUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/modules")
@RestController()
public class ModuleController {

    @Autowired
    private CreateModuleUseCase createModuleUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody CreateModuleRequest createModuleRequest) {
        String userId = request.getAttribute("userId").toString();

        try {
            var result = this.createModuleUseCase.execute(createModuleRequest, UUID.fromString(userId));
            return ResponseEntity.ok(result);

        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
