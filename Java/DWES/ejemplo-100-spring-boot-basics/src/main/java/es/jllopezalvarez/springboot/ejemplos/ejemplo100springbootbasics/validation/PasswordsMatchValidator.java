package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation;

import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto.MultiStepDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, MultiStepDto> {

    @Override
    public boolean isValid(MultiStepDto dto, ConstraintValidatorContext context) {

        System.out.printf("Validando contraseñas: %s - %s.\n", dto.getPassword(), dto.getConfirmPassword());
        // Si se recibe null o vacío, se considera válido.
        // Eso se debe controla con @NotNull, @NotEmpty o @NotBlank
        if (dto.getPassword() == null || dto.getConfirmPassword() == null ||
                dto.getPassword().isBlank() || dto.getConfirmPassword().isBlank()) {
            return true; // otras validaciones se encargan
        }

        boolean valid = dto.getPassword().equals(dto.getConfirmPassword());

        if (!valid) {
            // Como la validación es a nivel de clase, si no se hace nada,
            // el error será "global", no se asociará a un atributo específico.

            // Se desactiva el error por defecto, para que no haya error global.
            context.disableDefaultConstraintViolation();

            // Añadir un error asociado a un atributo específico del DTO.
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return valid;
    }
}
