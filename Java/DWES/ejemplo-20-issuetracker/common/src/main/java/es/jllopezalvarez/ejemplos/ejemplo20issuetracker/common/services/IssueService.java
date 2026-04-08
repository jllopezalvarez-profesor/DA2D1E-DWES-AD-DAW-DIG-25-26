package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services;

public interface IssueService {
    long countIssuesByAssigneeUserIdDerived(Long userId);
    long countIssuesByAssigneeUserIdSql(Long userId);
    long countIssuesByAssigneeUserIdJpql(Long userId);
}
