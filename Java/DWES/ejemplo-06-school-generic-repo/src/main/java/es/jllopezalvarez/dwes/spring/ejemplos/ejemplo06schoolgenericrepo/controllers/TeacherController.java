package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.controllers;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.entities.Teacher;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo06schoolgenericrepo.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping({"/teachers", "/teachers/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("teachers/index");

        mv.addObject("teachers", teacherService.findAll());

        return mv;
    }

    @GetMapping("/teachers/{id}")
    public ModelAndView detail(@PathVariable(name = "id") UUID teacherId) {
        ModelAndView mv = new ModelAndView("teachers/detail");
        System.out.println("En TeacherController.detail()");
        System.out.println("Id: " + teacherId);

        Optional<Teacher> teacher = teacherService.findById(teacherId);

        mv.addObject("isPresent", teacher.isPresent());
        if (teacher.isPresent()) {
            mv.addObject("teacher", teacher.orElseThrow());
        }


        return mv;
    }

}
