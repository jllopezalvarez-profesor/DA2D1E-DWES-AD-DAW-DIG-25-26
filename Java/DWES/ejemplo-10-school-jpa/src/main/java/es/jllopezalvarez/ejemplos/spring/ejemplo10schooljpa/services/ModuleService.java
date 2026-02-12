package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Module;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ModuleService {
    void addStudent(String moduleId, Long studentId);

    List<Module> findAll();
}
