package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Teacher;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                    .firstName("Nombre " + i)
                    .lastName("Apellido " + i)
                    .email(String.format("email%s@iesclaradelrey.es", i))
                    .build();

            teacherService.save(teacher);
        }
    }
}
