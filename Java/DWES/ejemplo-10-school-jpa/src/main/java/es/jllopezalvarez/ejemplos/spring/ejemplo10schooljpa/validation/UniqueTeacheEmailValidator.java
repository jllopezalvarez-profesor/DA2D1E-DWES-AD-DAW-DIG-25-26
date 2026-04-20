package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.validation;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.TeacherService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueTeacheEmailValidator implements ConstraintValidator<UniqueTeacherEmail, String> {

    private final TeacherService teacherService;

    public UniqueTeacheEmailValidator(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email.isBlank()) return true;
        return ! teacherService.existsByEmail(email);
    }
}
