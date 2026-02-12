package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAll();
}
