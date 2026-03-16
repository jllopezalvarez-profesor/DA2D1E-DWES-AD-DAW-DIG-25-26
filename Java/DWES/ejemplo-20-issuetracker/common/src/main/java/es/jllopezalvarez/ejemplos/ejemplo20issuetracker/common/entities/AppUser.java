package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @EqualsAndHashCode.Include
    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Basic(optional = false)
    @Column(nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(nullable = false)
    private String lastName;

    // region Relación para roles (AppUser <--> AppRole)

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Setter(AccessLevel.NONE)
    private Set<AppRole> roles = new HashSet<>();

    // Colección pública no modificable.
    public Set<AppRole> getRoles() {
        return Collections.unmodifiableSet(this.roles);
    }

    // Colección modificable para uso interno, dentro del paquete.
    Set<AppRole> getRolesInternal() {
        return this.roles;
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void addRole(AppRole role) {
        if (this.roles.add(role)) {
            role.getUsersInternal().add(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void removeRole(AppRole role) {
        if (this.roles.remove(role)) {
            role.getUsersInternal().remove(this);
        }
    }

    // endregion

    // region Relación para miembros del proyecto (AppUser <--> Project)

    @Builder.Default
    @ManyToMany(mappedBy = "members")
    @Setter(AccessLevel.NONE)
    private Set<Project> projects = new HashSet<>();

    // Colección pública no modificable.
    public Set<Project> getProjects() {
        return Collections.unmodifiableSet(this.projects);
    }

    // Colección modificable para uso interno, dentro del paquete.
    Set<Project> getProjectsInternal() {
        return this.projects;
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void addProject(Project project) {
        if (this.projects.add(project)) {
            project.getMembersInternal().add(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void removeProject(Project project) {
        if (this.projects.remove(project)) {
            project.getMembersInternal().remove(this);
        }
    }

    // endregion

    // region Relación para comentarios (AppUser <--> Comment)

    @Builder.Default
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private Set<Comment> comments = new LinkedHashSet<>();

    // Colección pública no modificable.
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(this.comments);
    }

    // Colección modificable para uso interno, dentro del paquete.
    Set<Comment> getCommentsInternal() {
        return this.comments;
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void addComment(Comment comment) {
        if (this.comments.add(comment)) {
            comment.setAuthor(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void removeComment(Comment comment) {
        if (this.comments.remove(comment)) {
            comment.setAuthor(this);
        }
    }

    // endregion

    // region Métodos auxiliares para combinar atributos

    public String getFullName() {
        return String.join(" ", this.firstName, this.lastName);
    }

    // endregion
}
