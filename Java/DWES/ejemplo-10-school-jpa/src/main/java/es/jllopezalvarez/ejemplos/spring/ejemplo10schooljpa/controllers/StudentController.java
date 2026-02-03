package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"", "/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("students/index");
        mv.addObject("students", studentService.findAll());
        return mv;
    }

    @GetMapping("/{studentId}")
    public ModelAndView detail(@PathVariable Long studentId){
        ModelAndView mv = new ModelAndView("students/detail");
        Student student = studentService.findById(studentId).orElseThrow();
        mv.addObject("student", student);
        return mv;
    }

    @GetMapping("{studentId}/add-module/{moduleId}")
    public void addModule(@PathVariable Long studentId,
                          @PathVariable String moduleId){
        studentService.addModule(studentId, moduleId);

    }



}
