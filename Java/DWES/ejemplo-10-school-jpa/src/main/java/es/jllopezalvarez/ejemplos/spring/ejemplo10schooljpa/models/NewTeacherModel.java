package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Department;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class NewTeacherModel {
    private String email;
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    private Long departmentId;

    // private Department department;
}
