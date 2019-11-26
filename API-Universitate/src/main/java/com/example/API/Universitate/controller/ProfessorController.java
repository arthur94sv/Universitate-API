package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.PutProfessor;
import com.example.API.Universitate.dto.curs.Curs;
import com.example.API.Universitate.dto.curs.PostPutCurs;
import com.example.API.Universitate.dto.professor.GetProfessor;
import com.example.API.Universitate.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
public class ProfessorController {
    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/professors")
    public List<GetProfessor> searchProfessor(@RequestParam(name = "nume", required = true) String nume,
                                              @RequestParam(name = "prenume", required = false) String prenume) {
        return professorService.searchProfessor(nume, prenume);
    }

    @PutMapping("/professors/{id}")
    public void updateProfessor(@PathVariable("id") int idProfessor, @RequestBody PutProfessor putProfessor) {
        professorService.updateProfessor(idProfessor, putProfessor);
    }

/*
                          COURSES
 */

    @PostMapping("/professors/{id}/courses")
    public Map<String, String> addCourse(@PathVariable("id") int idProfessor, @RequestBody @Valid PostPutCurs postPutCurs, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            FieldError fieldError = bindingResult.getFieldError();
            errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            return errors;
        } else {
            professorService.addCourse(idProfessor, postPutCurs);
            return null;
        }

    }

    @GetMapping("/professors/{id}/courses")
    public List<Curs> getCoursesForProfessor(@PathVariable("id") int idProfessor) {
        return professorService.getCoursesForProfessor(idProfessor);
    }

    @PutMapping("/courses/{id}")
    public void updateCourse(@PathVariable("id") int idCourse, @RequestBody PostPutCurs postPutCurs) {
        professorService.updateCourse(idCourse, postPutCurs);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable("id") int idCourse) {
        professorService.deleteCourse(idCourse);
    }
}
