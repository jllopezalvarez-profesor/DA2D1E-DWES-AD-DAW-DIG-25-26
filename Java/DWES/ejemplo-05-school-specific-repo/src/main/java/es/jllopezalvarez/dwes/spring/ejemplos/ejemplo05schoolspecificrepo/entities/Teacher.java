package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class Teacher {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate startDate;
}
