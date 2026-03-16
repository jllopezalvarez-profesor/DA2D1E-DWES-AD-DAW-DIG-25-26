package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Basic(optional = false)
    @Column(nullable = false)
    @Lob
    private String content;

    @Basic(optional = false)
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp()", updatable = false)
    private LocalDateTime createdAt;

    @Basic(optional = false)
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp()")
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    @Setter(AccessLevel.PACKAGE)
    private Issue issue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @Setter(AccessLevel.PACKAGE)
    private AppUser author;
}