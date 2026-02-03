package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.repositories;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
}
