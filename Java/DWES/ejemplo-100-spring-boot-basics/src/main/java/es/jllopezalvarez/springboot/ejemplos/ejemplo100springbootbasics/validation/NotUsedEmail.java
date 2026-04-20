package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotUsedEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotUsedEmail {
    String message() default "El correo electrónico ya está utilizado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
