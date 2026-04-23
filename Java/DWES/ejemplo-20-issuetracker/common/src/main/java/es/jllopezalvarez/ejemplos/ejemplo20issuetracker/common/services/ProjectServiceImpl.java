package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<Project> findAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "projectId").and(Sort.by(Sort.Direction.DESC, "name"));
        Pageable pageConfig = PageRequest.of(pageNumber, pageSize, sort);
        return projectRepository.findAll(pageConfig);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void delete(Long projectId) {
        if (!projectRepository.existsById(projectId)){
            // Lanzar excepción
            throw new EntityNotFoundException(String.format("El proyecto con id %d no existe.", projectId));
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll(Sort.by("name"));
    }
}
