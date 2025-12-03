package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
public class Student implements Entity<Long> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
}
