package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.validation.UniqueTeacherEmail;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewTeacherModel {
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es correcto")
    @Size.List({
            @Size(min = 10, message = "El correo electrónico debe tener ral menos tres caracteres"),
            @Size(max = 100, message = "El correo electrónico no puede tener más de cien caracteres")
    })
    @Length(max = 100)
    @UniqueTeacherEmail
    private String email;
    @NotNull
    @Length(max = 100)
    private String firstName;
    @NotNull
    @Length(max = 100)
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Past
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    private Long departmentId;

    // private Department department;
}
