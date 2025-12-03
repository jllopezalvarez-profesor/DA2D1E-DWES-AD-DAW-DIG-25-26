package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(UUID id);
    Teacher save(Teacher teacher);
    void delete(Teacher teacher);
}
