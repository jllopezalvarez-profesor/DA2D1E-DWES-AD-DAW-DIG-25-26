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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class AppRole {
    @Id
    private Long roleId;

    @EqualsAndHashCode.Include
    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 10)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String description;

    // region Relación para usuarios (AppUser <--> AppRole

    @Builder.Default
    @ManyToMany(mappedBy = "roles")
    @Setter(AccessLevel.NONE)
    private Set<AppUser> users = new HashSet<>();

    // Colección pública no modificable
    public Set<AppUser> getUsers() {
        return Collections.unmodifiableSet(this.users);
    }

    // Colección modificable para uso interno, dentro del paquete.
    Set<AppUser> getUsersInternal() {
        return this.users;
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void addUser(AppUser user) {
        if (this.users.add(user)) {
            user.getRolesInternal().add(this);
        }
    }

    // Utilidad para mantener sincronizados los dos extremos.
    public void removeUser(AppUser user) {
        if (this.users.remove(user)) {
            user.getRolesInternal().remove(this);
        }
    }

    // endregion

}
