package es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.controllers;


import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.entities.User;
import es.jllopezalvarez.dwes.spring.ejemplo04primeraweb.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    //@RequestMapping( method = RequestMethod.GET, path = "/")
    @GetMapping("/")
    //@PostMapping("/")
    // public String index() {
    public ModelAndView index() {
        System.out.println("HomeController.index()");

        User user = userService.findUserByEmail("jllopezalvarez@educa.madrid.org");

        return new ModelAndView("index",  "user", user);
    }

}
