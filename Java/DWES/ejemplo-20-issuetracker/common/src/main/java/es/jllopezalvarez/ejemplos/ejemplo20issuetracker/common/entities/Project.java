package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    // region Relación con miembros del proyecto (Project <--> AppUser)

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "project_member",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> members = new HashSet<>();

    // Colección pública no modificable
    public Set<AppUser> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    // Colección modificable para uso interno, dentro del paquete.
    Set<AppUser> getMembersInternal() {
        return this.members;
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void addMember(AppUser member) {
        if (this.members.add(member)) {
            member.getProjectsInternal().add(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos
    public void removeMember(AppUser member) {
        if (this.members.remove(member)) {
            member.getProjectsInternal().remove(this);
        }
    }

    // endregion

}