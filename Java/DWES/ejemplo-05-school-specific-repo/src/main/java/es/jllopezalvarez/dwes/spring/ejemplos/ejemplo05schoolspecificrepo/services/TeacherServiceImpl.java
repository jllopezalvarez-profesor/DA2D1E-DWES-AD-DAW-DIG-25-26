package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Teacher;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}
