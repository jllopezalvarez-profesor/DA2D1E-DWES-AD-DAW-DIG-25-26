package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Department;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Teacher;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models.NewTeacherModel;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.DepartmentRepository;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Integer teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Override
    public Teacher createNew(NewTeacherModel newTeacherModel) {
        Department department = departmentRepository
                .findById(newTeacherModel.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra el departamento con ID %s", newTeacherModel.getDepartmentId())));

        Teacher teacher = Teacher.builder()
                .email(newTeacherModel.getEmail())
                .firstName(newTeacherModel.getFirstName())
                .lastName(newTeacherModel.getLastName())
                .birthDate(newTeacherModel.getBirthDate())
                .startDate(newTeacherModel.getStartDate())
                .department(department)
                .build();

        return teacherRepository.save(teacher);

    }
}
