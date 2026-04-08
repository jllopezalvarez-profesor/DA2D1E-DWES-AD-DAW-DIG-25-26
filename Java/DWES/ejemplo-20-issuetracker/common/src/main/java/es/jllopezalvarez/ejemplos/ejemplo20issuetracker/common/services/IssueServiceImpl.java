package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories.IssueRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        List<Issue> issues = issueRepository.findAll();

        exportIssues(issues, document);


        return document;
    }

    private void exportIssues(List<Issue> issues, Document document) {
        Element issuesListElement =  document.createElement("issues");
        for (Issue issue: issues){
            Element issueElement = document.createElement("issue");
            issueElement.setAttribute("id", String.valueOf(issue.getIssueId()));
            issuesListElement.appendChild(issueElement);
            Element titleElement = document.createElement("title");
            titleElement.setTextContent(issue.getTitle());
            issueElement.appendChild(titleElement);


        }
        document.appendChild(issuesListElement);
    }
}
