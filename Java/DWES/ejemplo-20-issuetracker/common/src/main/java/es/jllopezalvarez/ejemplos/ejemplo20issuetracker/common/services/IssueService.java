package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public interface IssueService {
    long countIssuesByAssigneeUserIdDerived(Long userId);
    long countIssuesByAssigneeUserIdSql(Long userId);
    long countIssuesByAssigneeUserIdJpql(Long userId);
    Document exportAll() throws ParserConfigurationException;
    void importIssues(InputStream issuesStream) throws ParserConfigurationException, SAXException, IOException;
}
