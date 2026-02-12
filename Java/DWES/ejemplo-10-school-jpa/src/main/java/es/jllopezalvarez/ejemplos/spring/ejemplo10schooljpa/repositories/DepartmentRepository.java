package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Department;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends ListCrudRepository<Department, Long> {
}
