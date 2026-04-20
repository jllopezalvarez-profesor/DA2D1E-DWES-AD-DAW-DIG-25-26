package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinAgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {
    String message() default "No se alcanza la edad mínima";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
