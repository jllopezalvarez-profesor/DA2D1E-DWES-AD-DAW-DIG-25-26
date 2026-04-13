package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String title;

    @Basic(optional = false)
    @Column(nullable = false)
    @Lob
    private String description;

    @Builder.Default
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private IssueStatus status = IssueStatus.OPEN;

    @Basic(optional = false)
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp()", updatable = false)
    private LocalDateTime createdAt;

    @Basic(optional = false)
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp()")
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reporter_id", nullable = false)
    private AppUser reporter;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private AppUser assignee;

    // region Relación para comentarios (Issue <--> Comment)

    @Builder.Default
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<Comment> comments = new HashSet<>();

    // Builder seguro: permite añadir individualmente
    @Singular("comment")
    @Transient
    private Set<Comment> commentsBuilder = new HashSet<>();

    // Colección pública no modificable
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(this.comments);
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void addComment(Comment comment) {
        if (this.comments.add(comment)) {
            comment.setIssue(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void removeComment(Comment comment) {
        if (this.comments.remove(comment)) {
            comment.setIssue(null);
        }
    }

    // endregion

}
