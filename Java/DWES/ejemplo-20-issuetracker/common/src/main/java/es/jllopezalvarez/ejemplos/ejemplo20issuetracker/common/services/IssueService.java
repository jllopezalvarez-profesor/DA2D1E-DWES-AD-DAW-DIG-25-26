package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.IssueStatus;
import org.springframework.data.domain.Page;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public interface IssueService {
    long countIssuesByAssigneeUserIdDerived(Long userId);
    long countIssuesByAssigneeUserIdSql(Long userId);
    long countIssuesByAssigneeUserIdJpql(Long userId);
    Document exportAll() throws ParserConfigurationException;
    void importIssues(InputStream issuesStream) throws ParserConfigurationException, SAXException, IOException;

    List<Issue> findAll();

    String exportAllStax() throws XMLStreamException;

    void importIssuesStax(InputStream issuesStream) throws XMLStreamException;

    List<Issue> search(String title, IssueStatus status);

    Page<Issue> search(String text, IssueStatus status, Long projectId, Long assigneeId, LocalDate createdFrom, LocalDate createdTo, int page, int size, String sortBy, String sortDir);
}
