package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.controllers;


import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services.RandomService;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.Random;

@Controller
public class HomeController {

    private final UserService userService;
    private final RandomService randomService;
    private int requestNumber = 0;

    public HomeController(UserService userService, RandomService randomService) {
        this.userService = userService;
        this.randomService = randomService;
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
        Random random = new Random();
        mv.addObject("isRed", random.nextBoolean());
        mv.addObject("isBold", random.nextBoolean());
        mv.addObject("dayOfWeek", random.nextInt(0, 8));
        mv.addObject("names", randomService.getRandomNames(10));


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
