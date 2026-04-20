package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice(assignableTypes = {StudentController.class, TeacherController.class})
//@ControllerAdvice(basePackages = {"es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers"})
@ControllerAdvice
public class GlobalControllerAdvice {
    @Value("${shop.validation.client-validation-enabled}")
    private boolean clientValidationEnabled;

    @ModelAttribute("userIsAuthenticated")
    public boolean userIsAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) &&
                (auth.isAuthenticated()) &&
                (!(auth instanceof AnonymousAuthenticationToken));
    }

    @ModelAttribute(name = "clientValidationEnabled")
    public boolean getClientValidationEnabled() {
        return this.clientValidationEnabled;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(Exception e){
        ModelAndView mv = new ModelAndView("error.html.old");
        mv.addObject("errorMessage", e.getMessage());
        return mv;
    }

}
