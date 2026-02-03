package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.controllers;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.services.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("{moduleId}/add-student/{studentId}")
    public void addModule(@PathVariable String moduleId,
                          @PathVariable Long studentId) {
        moduleService.addStudent(moduleId, studentId);

    }
}
