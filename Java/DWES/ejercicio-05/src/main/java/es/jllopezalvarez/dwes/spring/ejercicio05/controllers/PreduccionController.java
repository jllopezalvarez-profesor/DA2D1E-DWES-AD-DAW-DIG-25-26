package es.jllopezalvarez.dwes.spring.ejercicio05.controllers;

import es.jllopezalvarez.dwes.spring.ejercicio05.services.PrediccionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PreduccionController {
    private final PrediccionService prediccionService;

    public PreduccionController(PrediccionService prediccionService) {
        this.prediccionService = prediccionService;
    }

    @GetMapping("/prediccion")
    public ModelAndView prediccion() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("prediccion");

        mv.addObject("prediccionesSemanales", prediccionService.getPrediccionSemanal());

        return mv;
    }
}
