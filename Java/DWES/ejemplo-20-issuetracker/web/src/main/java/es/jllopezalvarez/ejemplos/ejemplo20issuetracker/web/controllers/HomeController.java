package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping({"/"})
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }

    @GetMapping("/logout")
    public String logoutGet(){
        return this.home();
    }

}
