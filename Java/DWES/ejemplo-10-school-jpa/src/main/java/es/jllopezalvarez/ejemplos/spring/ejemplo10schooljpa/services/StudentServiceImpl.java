package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Module;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models.NewStudentModel;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.ModuleRepository;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ModuleRepository moduleRepository) {
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void addModule( Long studentId, String moduleId) {
        // Busco el estudiante.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No se ha encontrado el estudiante con id %d.", studentId)));

        // Ya tengo el estudiante, buscar el módulo que quiero añadir
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No se ha encontrado el módulo con id %s.", moduleId)));

        // Añado el módulo al estudiante
        student.getModules().add(module);

        // Guardar cambios
        studentRepository.save(student);
    }

    @Override
    public Student createNew(NewStudentModel newStudentModel) {

        Set<Module> modules = Set.copyOf(moduleRepository.findAllById(newStudentModel.getModules()));

        if (modules.size()!=newStudentModel.getModules().size()){
            throw new EntityNotFoundException("Alguno de los módulos no se han encontrado");
        }

        Student student = Student.builder()
                .documentId(newStudentModel.getDocumentId())
                .firstName(newStudentModel.getFirstName())
                .lastName(newStudentModel.getLastName())
                .birthDate(newStudentModel.getBirthDate())
                .hasScholarship(newStudentModel.isHasScholarship())
                .modules(modules)
                .build();

        return studentRepository.save(student);
    }
}
