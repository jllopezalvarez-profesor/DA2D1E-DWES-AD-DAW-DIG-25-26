package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Page<Project> findAll(int pageNumber, int pageSize);
    Optional<Project> findById(Long id);

    void delete(Long projectId);

    List<Project> findAll();
}
