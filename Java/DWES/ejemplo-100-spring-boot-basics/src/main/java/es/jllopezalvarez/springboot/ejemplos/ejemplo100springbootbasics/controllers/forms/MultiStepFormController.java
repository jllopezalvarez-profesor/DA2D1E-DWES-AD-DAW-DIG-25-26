package es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.controllers.forms;

import es.jllopezalvarez.springboot.ejemplos.ejemplo100springbootbasics.dto.MultiStepDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/forms/multi-step")
@SessionAttributes("dto")
public class MultiStepFormController {

    // Se usa la primera vez que un usuario accede al controlador.
    // Por la anotación @SessionAttributes
    @ModelAttribute("dto")
    public MultiStepDto createDto() {
        return new MultiStepDto();
    }

    // Paso 1 - GET
    @GetMapping("/step-one")
    public String stepOne() {
        return "/forms/multi-step/step-one";
    }

    // Paso 1 - POST
    @PostMapping("/step-one")
    public String procesarStepOne(
            @Validated(MultiStepDto.StepOne.class) @ModelAttribute("dto") MultiStepDto dto,
            BindingResult br) {

        if (br.hasErrors()) {
            return "forms/multi-step/step-one";
        }

        return "redirect:/forms/multi-step/step-two";
    }

    // Paso 2 - GET
    @GetMapping("/step-two")
    public String stepTwo() {
        return "forms/multi-step/step-two";
    }

    // Paso 2 - POST
    @PostMapping("/step-two")
    public String procesarStepTwo(
            @Validated(MultiStepDto.StepTwo.class) @ModelAttribute("dto") MultiStepDto dto,
            BindingResult br) {

        if (br.hasErrors()) {
            return "forms/multi-step/step-two";
        }

        return "redirect:/forms/multi-step/step-three";
    }

    // Paso 3 - GET
    @GetMapping("/step-three")
    public String stepThree() {
        return "forms/multi-step/step-three";
    }

    // Paso 3 - POST
    @PostMapping("/step-three")
    public String procesarStepThree(
            @Validated(MultiStepDto.StepThree.class) @ModelAttribute("dto") MultiStepDto dto,
            BindingResult br,
            SessionStatus status) {

        if (br.hasErrors()) {
            return "forms/multi-step/step-three";
        }

        // Aquí Se guardarían los datos del formulario

        status.setComplete(); // Limpiar sesión - Se encarga de quitar de la sesión el DTO

        // Redirigir al paso final
        // El DTO ya no estará disponible.
        return "/forms/multi-step/complete";
    }
}
    
