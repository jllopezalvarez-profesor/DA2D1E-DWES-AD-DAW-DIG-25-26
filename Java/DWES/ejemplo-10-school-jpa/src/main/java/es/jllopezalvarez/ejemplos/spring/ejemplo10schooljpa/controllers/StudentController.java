package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.Student;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.models.NewStudentModel;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.ModuleService;
import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final ModuleService moduleService;

    @Value("${shop.validation.client-validation-enabled}")
    private boolean clientValidationEnabled;

    public StudentController(StudentService studentService, ModuleService moduleService) {
        this.studentService = studentService;
        this.moduleService = moduleService;
    }

    @ModelAttribute(name = "clientValidationEnabled")
    public boolean getClientValidationEnabled(){
       return this.clientValidationEnabled;
    }

    @GetMapping({"", "/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("students/index");
        mv.addObject("students", studentService.findAll());
        return mv;
    }

    @GetMapping("/{studentId}")
    public ModelAndView detail(@PathVariable Long studentId) {
        ModelAndView mv = new ModelAndView("students/detail");
        Student student = studentService.findById(studentId).orElseThrow();
        mv.addObject("student", student);
        return mv;
    }

    @GetMapping("{studentId}/add-module/{moduleId}")
    public void addModule(@PathVariable Long studentId,
                          @PathVariable String moduleId) {
        studentService.addModule(studentId, moduleId);

    }

    @GetMapping("/new")
    public String newStudentGet(Model model) {
        model.addAttribute("student", new NewStudentModel());
        model.addAttribute("modules", moduleService.findAll());
        return "students/new";
    }


    @PostMapping("/new")
    public String newStudentPost(@ModelAttribute("student") NewStudentModel newStudentModel,
                                 Model model) {
        System.out.printf("Estudiante recibido:\n%s\n", newStudentModel);
        try {
            Student newStudent = studentService.createNew(newStudentModel);
            return String.format("redirect:/students/%d", newStudent.getStudentId());
        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            e.printStackTrace();
            model.addAttribute("modules", moduleService.findAll());
            return "students/new";
        }
    }


}
