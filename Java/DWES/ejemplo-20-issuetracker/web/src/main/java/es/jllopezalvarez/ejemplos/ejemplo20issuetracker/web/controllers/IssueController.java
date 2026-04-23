package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.web.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.dto.api.IssueDto;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.IssueStatus;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.*;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssueController {


    private final ProjectService projectService;
    private final AppUserService appUserService;
    private final IssueService issueService;

    public IssueController(ProjectService projectService, AppUserService appUserService, IssueService issueService) {
        this.projectService = projectService;
        this.appUserService = appUserService;
        this.issueService = issueService;
    }

    // Clase (record, para simplificar) para combinaciones texto/value
    public record SelectOption(String value, String text) {
    }




    // region Colecciones @ModelAttribute para campos de formulario de búsqueda.

    @ModelAttribute("issueStatuses")
    public List<IssueStatus> getIssueStatuses() {
        return List.of(IssueStatus.values());
    }

    @ModelAttribute("projects")
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @ModelAttribute("users")
    public List<AppUser> getUsers() {
        return appUserService.findAll();
    }

    @ModelAttribute("pageSizes")
    public List<Integer> getPageSizes() {
        return List.of(5, 10, 25, 50);
    }

    @ModelAttribute("sortFields")
    public List<SelectOption> getSortFields() {
        return List.of(
                new SelectOption("issueId", "ID"),
                new SelectOption("title", "Título"),
                new SelectOption("description", "Descripción"),
                new SelectOption("status", "Estado"),
                new SelectOption("createdAt", "Fecha de creación"),
                new SelectOption("updatedAt", "Fecha de modificación"),
                new SelectOption("project.name", "Proyecto")
        );
    }

    @ModelAttribute("sortDirection")
    public List<SelectOption> getSortDirections() {
        return List.of(
                new SelectOption("asc", "De menor a mayor"),
                new SelectOption("desc", "De mayor a menor")
        );
    }

    // endregion

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(required = false) IssueStatus status,
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {

        Page<Issue> foundIssuesPage = issueService.search(text, status, projectId, assigneeId, createdFrom, createdTo, page, size, sortBy, sortDir);

        // Añadir parámetros como atributos al modelo
        model.addAttribute("text", text);
        model.addAttribute("status", status);
        model.addAttribute("projectId", projectId);
        model.addAttribute("assigneeId", assigneeId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("size", size);
        model.addAttribute("results", foundIssuesPage.toList());
        model.addAttribute("totalPages", foundIssuesPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "issue/search";
    }

}
