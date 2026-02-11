package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Teacher;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping({"", "/"})
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("teachers/index");
        mv.addObject("teachers", teacherService.findAll());
        return mv;
    }

    @GetMapping("{teacherId}")
    public ModelAndView findById(@PathVariable Integer teacherId){
        ModelAndView mv = new ModelAndView("teachers/detail");
        Teacher teacher = teacherService.findById(teacherId).orElseThrow();
        mv.addObject("teacher", teacher);
        return mv;
    }


}
