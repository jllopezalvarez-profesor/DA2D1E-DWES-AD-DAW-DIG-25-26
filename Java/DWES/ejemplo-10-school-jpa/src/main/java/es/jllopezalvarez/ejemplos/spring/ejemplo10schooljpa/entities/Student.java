package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students",
        uniqueConstraints = {@UniqueConstraint(name = "un_nombre_apellidos"
                , columnNames = {"firstName", "lastName"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "stu_mod",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")}
    )
    private Set<Module> modules = new HashSet<>();
}
