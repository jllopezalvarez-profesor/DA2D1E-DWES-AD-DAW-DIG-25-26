package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students",
        uniqueConstraints = {@UniqueConstraint(name = "un_nombre_apellidos"
                , columnNames = {"firstName", "lastName"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Basic(optional = false)
    @Column(name = "dni", length = 10, unique = true, nullable = false)
    private String documentId;
    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;
    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate birthDate;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean hasScholarship;

    @ManyToMany
    @JoinTable(
            name = "stu_mod",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")}
    )
    private Set<Module> modules = new HashSet<>();
}
