package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Teacher;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models.NewTeacherModel;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll();
    Optional<Teacher> findById(Integer teacherId);

    Teacher createNew(NewTeacherModel newTeacherModel);

    Teacher update(Integer teacherId, NewTeacherModel newTeacherModel);
}
