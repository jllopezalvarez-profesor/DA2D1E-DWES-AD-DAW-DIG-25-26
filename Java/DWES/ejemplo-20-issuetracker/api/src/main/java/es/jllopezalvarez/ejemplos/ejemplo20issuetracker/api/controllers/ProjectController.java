package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.ProjectDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers.ProjectMapper;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    ResponseEntity<List<ProjectDto>> findAll() {
//        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok(projectService.findAll().stream().map(projectMapper::map).toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        // Aunque parezca redundante, el nombre del parámetro en @PathVariable es necesario
        // si no se compila con el modificador "-parameters", que se puede configurar en el
        // plugin de Maven. Sin ese modificador, en el bytecode no se incluye el nombre del
        // parámetro obtenido automáticamente, y hay que indicarlo de forma específica.



        Optional<Project> optionalProject = projectService.findById(id);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectMapper.map(optionalProject.orElseThrow()));
    }

//    private ProjectDto map(Project project) {
//        return ProjectDto.builder()
//                .projectId(project.getProjectId())
//                .name(project.getName())
//                .description(project.getDescription())
//                .build();
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long projectId){
        projectService.delete(projectId);
        return ResponseEntity.ok().build();
    }

}
