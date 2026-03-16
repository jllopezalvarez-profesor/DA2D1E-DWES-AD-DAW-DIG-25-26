package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}