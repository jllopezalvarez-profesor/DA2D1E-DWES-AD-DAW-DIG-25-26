package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Module;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.ModuleRepository;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final StudentRepository studentRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository, StudentRepository studentRepository) {
        this.moduleRepository = moduleRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(String moduleId, Long studentId) {
        // Buscar el módulo que quiero cambiar
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No se ha encontrado el módulo con id %s.", moduleId)));

        // Ya tengo el módulo. Busco el estudiante.
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No se ha encontrado el estudiante con id %d.", studentId)));

        // Añado el estudiante al módulo
        module.getStudents().add(student);

        // Guardar cambios
        moduleRepository.save(module);
    }

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }
}
