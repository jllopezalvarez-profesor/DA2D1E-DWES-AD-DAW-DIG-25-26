package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto;

import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation.MinAge;
import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation.NotUsedEmail;
import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation.PasswordsMatch;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/*
 * DTO para el ejemplo de formulario multipaso con almacenamiento en sesión entre pasos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@PasswordsMatch(groups = MultiStepDto.StepThree.class)
public class MultiStepDto {

    // Interfaces para los grupos de validación de cada uno de los tres pasos
    // Aunque se pueden definir por separado, en ficheros Java independientes,
    // al hacerlo aquí, se genera menos "ruido" en el árbol de ficheros del módulo.
    public interface StepOne {
    }

    public interface StepTwo {
    }

    public interface StepThree {
    }

    // --- PASO 1 ---
    @NotBlank(groups = StepOne.class)
    @Email(groups = StepOne.class)
    @NotUsedEmail(groups = StepOne.class)
    @EqualsAndHashCode.Include
    private String email;

    // --- PASO 2 ---
    @NotBlank(groups = StepTwo.class)
    private String firstName;

    @NotBlank(groups = StepTwo.class)
    private String lastName;

    @NotNull(groups = StepTwo.class)
    @Past(groups = StepTwo.class)
    @MinAge(groups = StepTwo.class, value = 18)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    // --- PASO 3 ---
    @NotBlank(groups = StepThree.class)
    @Size(min = 6, groups = StepThree.class)
    private String password;

    @NotBlank(groups = StepThree.class)
    @Size(min = 6, groups = StepThree.class)
    private String confirmPassword;

    @NotBlank(groups = StepThree.class)
    private String city;

    @AssertTrue(groups = StepThree.class)
    private boolean acceptsTermsOfUse;

}
