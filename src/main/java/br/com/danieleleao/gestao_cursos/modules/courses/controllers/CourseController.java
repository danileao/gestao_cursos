package br.com.danieleleao.gestao_cursos.modules.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_cursos.exceptions.ValidationException;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateCourseRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.useCases.CreateCourseUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody CreateCourseRequest createCourseRequest) {
        String userId = request.getAttribute("userId").toString();
        try {
            var result = this.createCourseUseCase.execute(createCourseRequest, userId);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
