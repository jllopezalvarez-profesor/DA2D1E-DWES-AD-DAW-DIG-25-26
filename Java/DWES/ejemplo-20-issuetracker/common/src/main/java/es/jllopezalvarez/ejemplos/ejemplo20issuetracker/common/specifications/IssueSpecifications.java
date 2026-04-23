package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.specifications;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.Issue;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.IssueStatus;
import jakarta.persistence.criteria.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class IssueSpecifications {

    // Las clases de utilidad no se pueden instanciar, y conviene que no se hereden (por eso es final)
    private IssueSpecifications() {
    }

    /**
     * Título o descripción contiene el texto (case-insensitive)
     */
    public static Specification<Issue> titleOrDescriptionContains(String text) {
        return (root, query, cb) -> {
            if (text == null || text.isBlank()) return null;

            String pattern = "%" + text.toLowerCase() + "%";
            query.distinct(true);
            return cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("description").as(String.class)), pattern));
        };
    }

    /**
     * Estado exacto
     */
    public static Specification<Issue> hasStatus(IssueStatus status) {
        // Forma más abreviada usando operador ternario
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    /**
     * Proyecto por ID
     */
    public static Specification<Issue> belongsToProject(Long projectId) {
        return (root, query, cb) ->
                projectId == null ? null : cb.equal(root.get("project").get("projectId"), projectId);
    }

    /**
     * Assignee por ID
     * Como el assignee puede ser null (no es obligatorio que la issue esté asignada)
     * al usar este criterio puede que se eliminen del resultado los registros de issues
     * no asignadas. Porque por defecto se hace un join normal.
     */
    public static Specification<Issue> assignedTo(Long assigneeId) {
        return (root, query, cb) ->
                assigneeId == null ? null : cb.equal(root.get("assignee").get("userId"), assigneeId);
    }

    /**
     * Assignee por ID, con left join
     * En esta se establece específicamente que se haga left join, que se conserven las issues sin assignee.
     */
    public static Specification<Issue> assignedToLeftJoin(Long assigneeId) {
        return (root, query, cb) -> {
            if (assigneeId == null) return null;
            Join<Issue, AppUser> assigneeJoin = root.join("assignee", JoinType.LEFT);
            return cb.equal(assigneeJoin.get("userId"), assigneeId);
        };
    }

    /**
     * Creado desde una fecha
     */
    public static Specification<Issue> createdFrom(LocalDate from) {
        return (root, query, cb) ->
                from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), LocalDateTime.from(from));
    }

    /**
     * Creado hasta una fecha
     */
    public static Specification<Issue> createdTo(LocalDate to) {
        return (root, query, cb) ->
                to == null ? null : cb.lessThan(root.get("createdAt"), LocalDateTime.from(to.plusDays(1)));
    }

    /**
     * Combina todos los criterios en una sola Specification
     */
    public static Specification<Issue> fromCriteria(
            String text,
            IssueStatus status,
            Long projectId,
            Long assigneeId,
            LocalDate createdFrom,
            LocalDate createdTo) {
        return Specification
                .where(titleOrDescriptionContains(text))
                .and(hasStatus(status))
                .and(belongsToProject(projectId))
                .and(assignedTo(assigneeId))
                .and(createdFrom(createdFrom))
                .and(createdTo(createdTo));
    }
}
