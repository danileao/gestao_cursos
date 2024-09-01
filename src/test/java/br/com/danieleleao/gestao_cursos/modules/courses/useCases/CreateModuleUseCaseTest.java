package br.com.danieleleao.gestao_cursos.modules.courses.useCases;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.danieleleao.gestao_cursos.modules.courses.dto.CreateModuleRequest;
import br.com.danieleleao.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.danieleleao.gestao_cursos.modules.courses.entities.ModuleEntity;
import br.com.danieleleao.gestao_cursos.modules.courses.repositories.CourseRepository;
import br.com.danieleleao.gestao_cursos.modules.courses.repositories.ModuleRepository;

@ExtendWith(MockitoExtension.class)
public class CreateModuleUseCaseTest {

    @InjectMocks
    CreateModuleUseCase createModuleUseCase;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ModuleRepository moduleRepository;

    @Test
    public void should_be_able_to_create_a_new_module() {
        UUID courseId = UUID.randomUUID();

        var createModuleRequest = CreateModuleRequest.builder()
                .title("title_test")
                .description("description_test")
                .order(1)
                .courseId(courseId)
                .build();

        UUID instructorId = UUID.randomUUID();

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(courseId);
        courseEntity.setInstructorId(instructorId);

        var moduleEntity = ModuleEntity.builder()
                .id(UUID.randomUUID())
                .build();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
        when(moduleRepository.findByCourseIdAndOrder(courseId, createModuleRequest.getOrder())).thenReturn(Optional.empty());
        when(moduleRepository.findByCourseIdAndTitle(courseId, createModuleRequest.getTitle())).thenReturn(Optional.empty());
        when(moduleRepository.save(any())).thenReturn(moduleEntity);

        try {
            var id = this.createModuleUseCase.execute(createModuleRequest, instructorId);

            assertNotNull(id);
        } catch (Exception e) {
            fail(e.getMessage());

        }
    }

}
