package br.com.danieleleao.gestao_cursos.modules.students;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danieleleao.gestao_cursos.modules.students.useCases.CreateEnrollmentUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private CreateEnrollmentUseCase createEnrollmentUseCase;

    @PostMapping("/enrollments/")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody UUID courseId) {
        var studentId = request.getAttribute("userId");

        try {
            var result = this.createEnrollmentUseCase.execute(courseId, UUID.fromString(studentId.toString()));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
