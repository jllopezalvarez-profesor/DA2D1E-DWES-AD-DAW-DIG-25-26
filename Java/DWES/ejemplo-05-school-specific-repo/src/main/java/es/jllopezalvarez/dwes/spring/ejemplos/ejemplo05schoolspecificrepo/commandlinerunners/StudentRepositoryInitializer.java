package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.commandlinerunners;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Student;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services.StudentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentRepositoryInitializer  implements CommandLineRunner {
    private static final int STUDENT_COUNT = 10;
    private final StudentService studentService;

    public StudentRepositoryInitializer(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < STUDENT_COUNT; i++) {
            Student student = Student.builder()
                    .firstName("Nombre " + i)
                    .lastName("Apellido " + i)
                    .email(String.format("email%s@iesclaradelrey.es", i))
                    .build();

            studentService.save(student);
        }
    }
}
