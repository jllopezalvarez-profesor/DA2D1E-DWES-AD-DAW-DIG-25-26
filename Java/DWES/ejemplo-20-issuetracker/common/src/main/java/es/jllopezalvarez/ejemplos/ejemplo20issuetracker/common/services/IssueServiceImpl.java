package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.IssueStatus;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Project;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.exceptions.AppUserNotFoundException;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.exceptions.ProjectNotFoundException;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories.IssueRepository;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.saxhandlers.IssuesImportSaxHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.stream.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final AppUserService appUserService;
    private final ProjectService projectService;

    public IssueServiceImpl(IssueRepository issueRepository, AppUserService appUserService, ProjectService projectService) {
        this.issueRepository = issueRepository;
        this.appUserService = appUserService;
        this.projectService = projectService;
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @Override
    public String exportAllStax() throws XMLStreamException {
        // Obtener incidencias
        List<Issue> issues = issueRepository.findAll();

        // Crear fábrica StAX
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        // Usamos StringWriter para devolver el XML como String
        StringWriter stringWriter = new StringWriter();

        // Crear writer XML
        XMLStreamWriter writer = factory.createXMLStreamWriter(stringWriter);

        // Escribir documento XML
        writer.writeStartDocument("1.0");

        // Elemento raíz
        writer.writeStartElement("issues");

        // Exportar incidencias
        exportIssues(issues, writer);

        // Cerrar raíz
        writer.writeEndElement();

        // Finalizar documento
        writer.writeEndDocument();

        writer.flush();
        writer.close();

        return stringWriter.toString();
    }

    @Override
    public long countIssuesByAssigneeUserIdDerived(Long userId) {
        return issueRepository.countAllByAssignee_UserId(userId);
    }

    @Override
    public long countIssuesByAssigneeUserIdSql(Long userId) {
        return issueRepository.countAllByAssigneeIdSql(userId);
    }

    @Override
    public long countIssuesByAssigneeUserIdJpql(Long userId) {
        return issueRepository.countAllByAssigneeIdJpql(userId);
    }

    @Override
    public Document exportAll() throws ParserConfigurationException {
        // Obtener el objeto DocumentBuilderFactory
        // Si se usa newDefaultInstance() se obtiene el parser DOM de la versión de Java que se use. Este parser
        // es siempre el mismo para esa versión de Java, no importa como se configure la aplicación. Si se usa
        // newInstance() se obtiene el parser en función de la configuración del sistema / aplicación, que puede
        // ser el mismo que con newDefaultInstance si no hay configuración extra sobre los parsers.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Obtener el objeto DocumentBuilder.
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Crear el nuevo documento XML
        Document document = builder.newDocument();
        // Opcional: fijar versión del XML. Esto no necesariamente se traduce en que el atributo "version" del
        // XML sea la versión indicada. La versión se define cuando se transforma el XML en texto, usando el
        // objeto Transformer. El encoding no se puede establecer aquí. Se hace cuando se transforma.
        document.setXmlVersion("1.0");

        // Obtener las incidencias del repositorio
        List<Issue> issues = issueRepository.findAll();

        // Exportar las incidencias
        exportIssues(issues, document);

        // Devolver el documento generado
        return document;
    }

    @Override
    public void importIssues(InputStream issuesStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        IssuesImportSaxHandler handler = new IssuesImportSaxHandler(projectService, appUserService);

        parser.parse(issuesStream, handler);

        System.out.printf("Se han encontrado %d issues en el XML\n", handler.getIssues().size());

        handler.getIssues().forEach(issue -> System.out.println(issue.getTitle()));

        issueRepository.saveAll(handler.getIssues());


    }

    @Override
    public void importIssuesStax(InputStream issuesStream) throws XMLStreamException {
        List<Issue> issues = readIssuesFromXmlStax(issuesStream);
        issueRepository.saveAll(issues);
    }

    private void exportIssues(List<Issue> issues, XMLStreamWriter writer) throws XMLStreamException {

        for (Issue issue : issues) {

            writer.writeStartElement("issue");

            // Atributos principales
            writer.writeAttribute("issueId", String.valueOf(issue.getIssueId()));
            writer.writeAttribute("status", String.valueOf(issue.getStatus()));
            writer.writeAttribute("createdAt", String.valueOf(issue.getCreatedAt()));
            writer.writeAttribute("updatedAt", String.valueOf(issue.getUpdatedAt()));

            writer.writeAttribute("projectId", String.valueOf(issue.getProject().getProjectId()));
            writer.writeAttribute("reporterId", String.valueOf(issue.getReporter().getUserId()));

            if (issue.getAssignee() != null) {
                writer.writeAttribute("assigneeId",
                        String.valueOf(issue.getAssignee().getUserId()));
            }

            // Elemento title
            writer.writeStartElement("title");
            writer.writeCharacters(issue.getTitle() != null ? issue.getTitle() : "");
            writer.writeEndElement();

            // Elemento description
            writer.writeStartElement("description");
            writer.writeCharacters(issue.getDescription() != null ? issue.getDescription() : "");
            writer.writeEndElement();

            writer.writeEndElement(); // </issue>
        }
    }


    private void exportIssues(List<Issue> issues, Document document) {
        // Crear elemento raíz para el documento.
        Element issuesListElement = document.createElement("issues");
        // Procesar cada una de las incidencias
        for (Issue issue : issues) {
            // Crear elemento para la incidencia.
            Element issueElement = document.createElement("issue");

            // Añadir el elemento a la lista de elementos. Se puede añadir antes de modificarlo,
            // porque se trabaja con referencias a objetos, que pueden modificarse, porque son mutables.
            issuesListElement.appendChild(issueElement);

            // Añadir atributos al elemento de la incidencia
            issueElement.setAttribute("issueId", String.valueOf(issue.getIssueId()));
            issueElement.setAttribute("status", String.valueOf(issue.getStatus()));
            // La fecha / hora se formateará con ISO 8601
            issueElement.setAttribute("createdAt", String.valueOf(issue.getCreatedAt()));
            issueElement.setAttribute("updatedAt", String.valueOf(issue.getUpdatedAt()));

            // Añadir elemento title al elemento de la incidencia.
            Element titleElement = document.createElement("title");
            titleElement.setTextContent(issue.getTitle());
            issueElement.appendChild(titleElement);

            // Añadir elemento description al elemento de la incidencia.
            Element descriptionElement = document.createElement("description");
            descriptionElement.setTextContent(issue.getDescription());
            issueElement.appendChild(descriptionElement);

            // Añadir atributos para relaciones obligatorias (project, reporter)
            issueElement.setAttribute("projectId", String.valueOf(issue.getProject().getProjectId()));
            issueElement.setAttribute("reporterId", String.valueOf(issue.getReporter().getUserId()));
            if (issue.getAssignee() != null) {
                issueElement.setAttribute("assigneeId", String.valueOf(issue.getAssignee().getUserId()));
            }
        }
        // Añadir la lista de elementos al documento. Esto puede hacerse antes del for sin problemas
        document.appendChild(issuesListElement);
    }

    private List<Issue> readIssuesFromXmlStax(InputStream inputStream)
            throws XMLStreamException {

        List<Issue> issues = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

        Issue currentIssue = null;

        while (reader.hasNext()) {
            reader.next();

            if (reader.isStartElement()) {

                String tag = reader.getLocalName();

                if ("issue".equals(tag)) {
                    currentIssue = new Issue();
                    setIssueAttributesStax(reader, currentIssue);
                } else if ("title".equals(tag)) {
                    String text = reader.getElementText();
                    currentIssue.setTitle(text);
                } else if ("description".equals(tag)) {
                    String text = reader.getElementText();
                    currentIssue.setDescription(text);
                }
            } else if (reader.isEndElement()) {

                if ("issue".equals(reader.getLocalName())) {
                    issues.add(currentIssue);
                    currentIssue = null;
                }
            }
        }


        return issues;
    }


    private void setIssueAttributesStax(XMLStreamReader reader, Issue issue) {

        issue.setStatus(IssueStatus.valueOf(reader.getAttributeValue(null, "status")));

        issue.setCreatedAt(LocalDateTime.parse(
                reader.getAttributeValue(null, "createdAt"))
        );

        issue.setUpdatedAt(LocalDateTime.now());

        // PROJECT
        String projectIdString = reader.getAttributeValue(null, "projectId");
        try {
            Long projectId = Long.parseLong(projectIdString);
            Project project = projectService.findById(projectId).orElseThrow();
            issue.setProject(project);
        } catch (Exception ex) {
            throw new ProjectNotFoundException(
                    "No se encuentra el proyecto con id '" + projectIdString + "'"
            );
        }

        // REPORTER
        String reporterIdString = reader.getAttributeValue(null, "reporterId");
        try {
            Long reporterId = Long.parseLong(reporterIdString);
            AppUser reporter = appUserService.findById(reporterId).orElseThrow();
            issue.setReporter(reporter);
        } catch (Exception ex) {
            throw new AppUserNotFoundException(
                    "No se ha encontrado el usuario 'reporter' con id '" + reporterIdString + "'"
            );
        }

        // ASSIGNEE (opcional)
        String assigneeIdString = reader.getAttributeValue(null, "assigneeId");
        if (assigneeIdString != null) {
            try {
                Long assigneeId = Long.parseLong(assigneeIdString);
                AppUser assignee = appUserService.findById(assigneeId).orElseThrow();
                issue.setAssignee(assignee);
            } catch (Exception ex) {
                throw new AppUserNotFoundException(
                        "No se ha encontrado el usuario 'assignee' con id '" + assigneeIdString + "'"
                );
            }
        }
    }

}
