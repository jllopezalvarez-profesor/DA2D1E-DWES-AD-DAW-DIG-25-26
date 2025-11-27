package es.jllopezalvarez.dwes.spring.ejercicio05.controllers;

import es.jllopezalvarez.dwes.spring.ejercicio05.entities.Persona;
import es.jllopezalvarez.dwes.spring.ejercicio05.services.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/personas")
    public ModelAndView listAll(){
        System.out.println("PersonaController.listAll()");
        ModelAndView mv = new ModelAndView( "personas/list");
        Collection<Persona> personas = personaService.find(10);
        mv.addObject("personas", personas);
        return mv;

    }

    @GetMapping("/personas/one")
    public String listOne(){
        System.out.println("PersonaController.listOne()");
        return "personas/one";
    }


    //      /personas/uno
}
