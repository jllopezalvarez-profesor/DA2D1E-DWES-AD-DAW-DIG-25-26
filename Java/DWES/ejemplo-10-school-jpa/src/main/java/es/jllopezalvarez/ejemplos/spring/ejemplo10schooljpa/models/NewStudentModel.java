package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class NewStudentModel {
    private String documentId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    // private Set<Module> modules = new HashSet<>();
}
