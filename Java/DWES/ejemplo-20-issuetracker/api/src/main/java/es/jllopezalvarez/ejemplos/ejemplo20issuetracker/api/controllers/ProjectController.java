package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.api.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.ProjectDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.mappers.ProjectMapper;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Page<ProjectDto>> findAll(
            @RequestParam(value = "p", defaultValue = "0") int pageNumber,
            @RequestParam(value = "ps", defaultValue = "1")  int pageSize) {
//        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Page<Project> projectsPage = projectService.findAll(pageNumber, pageSize);

        return ResponseEntity.ok(projectsPage.map(projectMapper::map));
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
    public ResponseEntity<Void> delete(@PathVariable("id") Long projectId) {
        projectService.delete(projectId);
        return ResponseEntity.ok().build();
    }

}
