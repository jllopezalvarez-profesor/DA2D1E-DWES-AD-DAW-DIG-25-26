package es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.controllers;

import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.entities.Student;
import es.jllopezalvarez.dwes.spring.ejemplos.ejemplo05schoolspecificrepo.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

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

    @GetMapping("/students/{id}")
    public ModelAndView detail(@PathVariable(name = "id") Long studentId) {
        ModelAndView mv = new ModelAndView("students/detail");
        System.out.println("En StudentController.detail()");
        System.out.println("Id: " + studentId);

        Optional<Student> student = studentService.findById(studentId);

        mv.addObject("isPresent", student.isPresent());
        if (student.isPresent()) {
            mv.addObject("student", student.orElseThrow());
        }


        return mv;
    }

}
