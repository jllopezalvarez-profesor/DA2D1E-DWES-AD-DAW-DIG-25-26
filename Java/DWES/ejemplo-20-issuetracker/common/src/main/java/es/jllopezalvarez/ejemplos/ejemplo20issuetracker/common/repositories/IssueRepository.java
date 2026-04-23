package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<Issue, Long>, JpaSpecificationExecutor<Issue> {
    // Cuenta por usuario asignado con consulta derivada
    long countAllByAssignee_UserId(Long userId);

    // Cuenta por usuario asignado con consulta personalizada SQL
    @Query(value = "select count(1) from issue where assignee_id = :userId", nativeQuery = true)
    long countAllByAssigneeIdSql(Long userId);

    // Cuenta por usuario asignado con consulta personalizada JPQL
    @Query(value = "select count(issueId) from Issue where assignee.userId = :userId ")
    long countAllByAssigneeIdJpql(@Param("userId") Long userIdentifier);


}