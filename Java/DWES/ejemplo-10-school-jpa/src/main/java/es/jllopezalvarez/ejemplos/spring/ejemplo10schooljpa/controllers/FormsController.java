package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("forms-examples")
public class FormsController {

    @GetMapping("example-01")
    public String getExample01() {
        return "forms-examples/example-01";
    }

    @PostMapping("example-01")
    public String postExample01(
            @RequestParam(name = "first-name") String firstName,
            Model model) {
        System.out.printf("Nombre recibido: %s\n", firstName);
        model.addAttribute("firstName", firstName);
        return "forms-examples/example-01";
    }

    @GetMapping("example-02")
    public String getExample02() {
        return "forms-examples/example-02";
    }

    @PostMapping("example-02")
    public String postExample02(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam(required = false) LocalDate birthDate,
            //@RequestParam(defaultValue = "170") int height,
            @RequestParam(required = false) Integer height,
            @RequestParam String birthProvince,
            Model model) {
        System.out.printf("Nombre recibido: %s\n", firstName);
        System.out.printf("Apellido recibido: %s\n", lastName);
        System.out.printf("Fecha de nacimiento recibida: %s\n", birthDate);
        System.out.printf("Estatura: %s\n", height);
        System.out.printf("Provincia de nacimiento recibida: %s\n", birthProvince);


        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("birthDate", birthDate); // Se añade con el mismo nombre de la variable.
        model.addAttribute("height", height);
        model.addAttribute("birthProvince", birthProvince);

        if (firstName.isBlank() || lastName.isBlank() || birthDate == null || birthProvince.isBlank()){
            model.addAttribute("errorMessage", "Hay algo mal...");
            return "forms-examples/example-02";
        }

        return "forms-examples/ok";

    }


}
