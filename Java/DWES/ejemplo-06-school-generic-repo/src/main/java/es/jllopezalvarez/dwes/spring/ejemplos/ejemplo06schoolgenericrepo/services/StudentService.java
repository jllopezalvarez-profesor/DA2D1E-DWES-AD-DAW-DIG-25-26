package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.services;


import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void delete(Student student);
}
