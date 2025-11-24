package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.controllers;


import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Random;

@Controller
public class HomeController {

    private final UserService userService;
    private int requestNumber = 0;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    //@RequestMapping( method = RequestMethod.GET, path = "/")
    @GetMapping("/")
    //@PostMapping("/")
    // public String index() {
    public ModelAndView index() {
        requestNumber++;
        System.out.printf("HomeController.index() - Petición número %d\n", requestNumber);

        User userInDb = userService.findUserByEmail("jllopezalvarez@educa.madrid.org");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("user", userInDb);
        mv.addObject("currentTime", LocalTime.now().toString());
        mv.addObject("isRed", new Random().nextBoolean());
        mv.addObject("isBold", new Random().nextBoolean());


        return mv;

//         return new ModelAndView("index", "user", userInDb);
    }


    // Metodo para responder a la URL /help
    // este método devuelve String, y por eso,
    // tal y como está definido, no se pueden pasar datos a la vista
    // Hay que asociar la URL /help con este método del controlador
    @GetMapping("/help")
    public String help(){
        System.out.println("HomeController.help()");
        // Devolver la vista que se quiere generar.
        // Spring busca una plantilla con el nombre indicado,
        // y la extensión .html. Donde la busca y la extensión es personalizable
        return "help";
    }

}
