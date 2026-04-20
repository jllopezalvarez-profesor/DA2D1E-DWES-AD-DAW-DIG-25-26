package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotUsedEmailValidator implements ConstraintValidator<NotUsedEmail, String> {

    // Lista de correo no válidos. Simulan los correos en la BD.
    // En un caso real este validador podría ser @Component, inyectar un
    // servicio o repositorio, y comprobar en la BD si existe el correo.
    private final List<String> notValidEmails = List.of(
            "admin@example.com",
            "user1@example.com",
            "user2@example.com",
            "user3@example.com"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // Si se recibe null o vacío, se considera válido.
        // Eso se debe controla con @NotNull, @NotEmpty o @NotBlank
        if (value == null || value.isBlank()) return true;
        // Es válido si no está entre los correos incorrectos
        return !notValidEmails.contains(value);
    }
}
