package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueTeacheEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTeacherEmail {
    String message() default "Ya existe este valor en la BD";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
