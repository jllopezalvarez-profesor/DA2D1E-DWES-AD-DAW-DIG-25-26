package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Student;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentRepositoryInitializer implements CommandLineRunner {
    private static final int STUDENT_COUNT = 10;
    private final StudentService studentService;

    public StudentRepositoryInitializer(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < STUDENT_COUNT; i++) {
            Student student = Student.builder()
                    .id((long) i + 1)
                    .firstName("Nombre " + i)
                    .lastName("Apellido " + i)
                    .email(String.format("email%s@iesclaradelrey.es", i))
                    .build();

            studentService.save(student);
        }
    }
}
