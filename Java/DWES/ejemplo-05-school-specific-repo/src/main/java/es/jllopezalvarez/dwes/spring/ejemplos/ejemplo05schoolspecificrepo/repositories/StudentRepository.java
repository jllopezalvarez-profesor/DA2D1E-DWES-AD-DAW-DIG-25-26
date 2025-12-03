package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.repositories;


import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void delete(Student student);
}
