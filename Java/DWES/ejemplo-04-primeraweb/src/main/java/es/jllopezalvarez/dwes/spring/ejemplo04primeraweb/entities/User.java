package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class User {
    @EqualsAndHashCode.Include
    private final String email;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
}
