package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Basic(optional = false)
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String location;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers = new ArrayList<>();
}
