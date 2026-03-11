package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class AppRol {
    @Id
    private String rolId;
    @Basic(optional = false)
    private String rolName;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();
}
