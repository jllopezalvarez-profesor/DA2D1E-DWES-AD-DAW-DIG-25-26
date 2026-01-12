package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }
}
