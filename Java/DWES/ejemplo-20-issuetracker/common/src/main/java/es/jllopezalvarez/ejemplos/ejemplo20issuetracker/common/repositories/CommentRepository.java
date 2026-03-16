package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.repositories;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}