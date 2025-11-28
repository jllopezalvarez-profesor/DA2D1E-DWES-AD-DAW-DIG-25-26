package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.repositories;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    final SortedMap<Long, Student> students = new TreeMap<>();

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Student save(Student student) {
        // Si el estudiante llega con id null es que es nuevo.
        if (student.getId() == null) {
            synchronized (students) {
                student.setId(getNewId());
                students.put(student.getId(), student);
            }
            return student;
        }

        // Ni no tenía id null, pero no se encuentra, error
        if (students.get(student.getId()) == null) {
            throw new NoSuchElementException("Student with id " + student.getId() + " not found");
        }

        // Actualizar el existente
        return students.put(student.getId(), student);
    }


    @Override
    public void delete(Student student) {
        if (!students.containsKey(student.getId())) {
            throw new NoSuchElementException("Student with id " + student.getId() + " not found");
        }
        students.remove(student.getId());
    }

    private Long getNewId() {

        return students.isEmpty() ? 1 : students.lastKey() + 1;

//        long max = students
//                .keySet()
//                .stream()
//                .mapToLong(k -> k)
//                .max()
//                .orElse(0);
//        return max + 1;
    }
}
