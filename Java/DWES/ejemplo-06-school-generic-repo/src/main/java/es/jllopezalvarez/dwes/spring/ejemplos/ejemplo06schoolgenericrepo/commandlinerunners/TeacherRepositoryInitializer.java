package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Teacher;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherRepositoryInitializer implements CommandLineRunner {
    private static final int TEACHER_COUNT = 10;
    private final TeacherService teacherService;

    public TeacherRepositoryInitializer(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < TEACHER_COUNT; i++) {
            Teacher teacher = Teacher.builder()
                    .id(UUID.randomUUID())
                    .firstName("Nombre " + i)
                    .lastName("Apellido " + i)
                    .email(String.format("email%s@iesclaradelrey.es", i))
                    .build();

            teacherService.save(teacher);
        }
    }
}
