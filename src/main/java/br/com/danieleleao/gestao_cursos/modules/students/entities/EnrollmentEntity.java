package br.com.danieleleao.gestao_cursos.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.danieleleao.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.danieleleao.gestao_cursos.modules.users.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Table
@Entity(name = "enrollments")
@Data
@Builder
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "course_id")
    private UUID courseId;

    @ManyToOne()
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private CourseEntity course;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
