package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {

    private int value;

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate fecha, ConstraintValidatorContext context) {
        // Si se recibe null, se considera válido.
        // Eso se debe controla con @NotNull.
        if (fecha == null) return true;
        int edad = Period.between(fecha, LocalDate.now()).getYears();
        return edad >= value;
    }
}
