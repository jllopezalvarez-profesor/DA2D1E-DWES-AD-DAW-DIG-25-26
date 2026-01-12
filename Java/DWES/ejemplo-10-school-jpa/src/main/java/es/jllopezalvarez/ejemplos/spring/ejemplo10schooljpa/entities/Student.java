package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "students",
        uniqueConstraints = { @UniqueConstraint(name = "un_nombre_apellidos"
                , columnNames = {"firstName", "lastName"})  })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Basic(optional = false)
    @Column(name="dni", length = 10, unique = true, nullable = false )
    private String documentId;
    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;
    private LocalDate birthDate;
}
