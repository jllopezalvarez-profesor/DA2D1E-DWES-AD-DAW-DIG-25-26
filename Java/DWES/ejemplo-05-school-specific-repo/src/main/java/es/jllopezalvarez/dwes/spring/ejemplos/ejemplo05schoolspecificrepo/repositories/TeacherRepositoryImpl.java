package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.repositories;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Teacher;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {


    private final SortedMap<UUID, Teacher> teachers = new TreeMap<>();

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(teachers.values());
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return Optional.ofNullable(teachers.get(id));
    }

    @Override
    public Teacher save(Teacher teacher) {
        // Si el estudiante llega con id null es que es nuevo.
        if (teacher.getId() == null) {
                teacher.setId(UUID.randomUUID());
                teachers.put(teacher.getId(), teacher);
            return teacher;
        }

        // Ni no tenía id null, pero no se encuentra, error
        if (teachers.get(teacher.getId()) == null) {
            throw new NoSuchElementException("Teacher with id " + teacher.getId() + " not found");
        }

        // Actualizar el existente
        return teachers.put(teacher.getId(), teacher);
    }


    @Override
    public void delete(Teacher teacher) {
        if (!teachers.containsKey(teacher.getId())) {
            throw new NoSuchElementException("Teacher with id " + teacher.getId() + " not found");
        }
        teachers.remove(teacher.getId());
    }


}
