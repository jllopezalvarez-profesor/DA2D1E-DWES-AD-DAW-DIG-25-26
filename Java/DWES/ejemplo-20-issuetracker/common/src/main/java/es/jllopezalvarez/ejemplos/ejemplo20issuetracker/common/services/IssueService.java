package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;

public interface IssueService {
    long countIssuesByAssigneeUserIdDerived(Long userId);
    long countIssuesByAssigneeUserIdSql(Long userId);
    long countIssuesByAssigneeUserIdJpql(Long userId);
    Document exportAll() throws ParserConfigurationException;
}
