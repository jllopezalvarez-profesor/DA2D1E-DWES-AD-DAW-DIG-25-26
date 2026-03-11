package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.dto;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Module;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long studentId;
    private String documentId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private boolean hasScholarship;

    public StudentDto(Student st) {
        this. studentId=st.getStudentId();
        this.documentId = st.getDocumentId();
        this.firstName = st.getFirstName();
        this.lastName = st.getLastName();
        this.birthDate = st.getBirthDate();
        this.hasScholarship = st.isHasScholarship();
    }
}
