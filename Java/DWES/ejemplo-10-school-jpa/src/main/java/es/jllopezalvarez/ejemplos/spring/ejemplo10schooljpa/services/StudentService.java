package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long studentId);
}
