package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.saxhandlers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.IssueStatus;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.exceptions.AppUserNotFoundException;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.exceptions.ProjectNotFoundException;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.AppUserService;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.ProjectService;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IssuesImportSaxHandler extends DefaultHandler {

    @Getter
    private final List<Issue> issues = new ArrayList<>();
    private Issue currentIssue;

    private final StringBuilder currentTextBuilder = new StringBuilder();

    private boolean inIssue = false;
    private boolean inTitle = false;
    private boolean inDescription = false;

    private final ProjectService projectService;
    private final AppUserService appUserService;

    public IssuesImportSaxHandler(ProjectService projectService, AppUserService appUserService) {
        this.projectService = projectService;
        this.appUserService = appUserService;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "issue":
                this.inIssue = true;
                // Crear objeto para la nueva Issue
                this.currentIssue = new Issue();
                // Obtener los valores necesarios de los atributos.
                // Se hace en método aparte para que quede más límpio este startElement
                setCurrentIssueAttributes(attributes);
                break;
            case "title":
                this.inTitle = true;
                break;
            case "description":
                this.inDescription = true;
                break;
            default:
                System.out.printf("Se ha encontrado un elemento que no interesa: %s\n", qName);
        }
    }

    private void setCurrentIssueAttributes(Attributes attributes) {
        // Estado de la entidad. Se convierte desde texto.
        currentIssue.setStatus(IssueStatus.valueOf(attributes.getValue("status")));

        // Fecha de creación.
        currentIssue.setCreatedAt(LocalDateTime.parse(attributes.getValue("createdAt")));

        // Proyecto. Se usa el servicio inyectado por constructor para buscarlo.
        String projectIdString = attributes.getValue("projectId");
        try {
            Long projectId = Long.parseLong(projectIdString);
            Project project = projectService.findById(projectId).orElseThrow();
            currentIssue.setProject(project);
        } catch (Exception ex) {
            throw new ProjectNotFoundException(String.format("No se encuentra el proyecto con id '%s'", projectIdString));
        }

        // Usuario que reportó. Usamos el servicio inyectado por constructor para buscarlo.
        String reporterIdString = attributes.getValue("reporterId");
        try {
            Long reporterId = Long.parseLong(reporterIdString);
            AppUser reporter = appUserService.findById(reporterId).orElseThrow();
            currentIssue.setReporter(reporter);
        } catch (Exception e) {
            throw new AppUserNotFoundException(String.format("No se ha encontrado el usuario 'reporter' con id '%s'", reporterIdString));
        }

        // Usuario asignado. Usamos el servicio inyectado por constructor para buscarlo.
        String assigneeIdString = attributes.getValue("assigneeId");
        if (assigneeIdString != null) {
            try {
                Long assigneeId = Long.parseLong(assigneeIdString);
                AppUser assignee = appUserService.findById(assigneeId).orElseThrow();
                currentIssue.setAssignee(assignee);
            } catch (Exception e) {
                throw new AppUserNotFoundException(String.format("No se ha encontrado el usuario 'assignee' con id '%s'", assigneeIdString));
            }
        }

        // Atributos con valor automático:
        this.currentIssue.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "issue":
                this.inIssue = false;
                this.issues.add(currentIssue);
//                this.currentIssue = null;
                break;
            case "title":
                this.inTitle = false;
                // guardar el título en el objeto currentIssue
                System.out.printf("En fin de titulo - %s\n", currentTextBuilder.toString());
                this.currentIssue.setTitle(currentTextBuilder.toString());
                this.currentTextBuilder.setLength(0);
                break;
            case "description":
                this.inDescription = false;
                // guardar la descripción en el objeto currentIssue
                System.out.printf("En fin de descripción - %s\n", currentTextBuilder.toString());
                this.currentIssue.setDescription(currentTextBuilder.toString());
                this.currentTextBuilder.setLength(0);
                break;
            default:
                System.out.printf("Se ha encontrado un fin de elemento que no nos interesa: %s\n", qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Solo se guarda el texto si se está en un nodo en el que interese capturar texto
        if (this.inTitle || this.inDescription) {
            this.currentTextBuilder.append(ch, start, length);
        }
    }
}
