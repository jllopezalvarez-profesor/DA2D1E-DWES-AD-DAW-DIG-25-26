package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.controllers;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"/students", "/students/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("students/index");

        mv.addObject("students", studentService.findAll());

        return mv;
    }

}
