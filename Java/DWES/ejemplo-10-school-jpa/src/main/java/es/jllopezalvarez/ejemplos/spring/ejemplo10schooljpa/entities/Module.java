package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modules")
public class Module {
    @Id
    private String moduleId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 2000)
    private String description;

    @ManyToMany(mappedBy = "modules")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getModules().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getModules().remove(this);
    }
}
