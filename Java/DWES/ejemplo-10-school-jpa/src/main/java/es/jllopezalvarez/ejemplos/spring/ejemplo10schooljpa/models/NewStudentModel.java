package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
public class NewStudentModel {
    private String documentId;
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private boolean hasScholarship;
    private Set<String> modules = new HashSet<>();
}
