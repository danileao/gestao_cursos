package br.com.danieleleao.gestao_cursos.modules.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_cursos.exceptions.ValidationException;
import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateCourseRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.useCases.CreateCourseUseCase;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateCourseRequest createCourseRequest) {
        try {
            var result = this.createCourseUseCase.execute(createCourseRequest);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
