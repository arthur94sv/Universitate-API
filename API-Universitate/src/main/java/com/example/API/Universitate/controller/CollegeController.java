package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.College;
import com.example.API.Universitate.dto.Department;
import com.example.API.Universitate.dto.professor.GetProfessorForCollege;
import com.example.API.Universitate.dto.professor.PostProfessorForCollege;
import com.example.API.Universitate.service.CollegeService;
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
public class CollegeController {

    private CollegeService collegeService;


    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("/colleges")
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }

    @PostMapping("/colleges")
    public Map<String, String> addCollege(@RequestBody @Valid College college, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            FieldError fieldError = bindingResult.getFieldError();
            errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            return errors;
        } else {
            collegeService.addCollege(college);
            return null;
        }
    }

    @PutMapping("/colleges/{id}")
    public void updateCollege(@PathVariable("id") int idColleges, @RequestBody College college) {
        collegeService.updateCollege(idColleges, college);
    }


    @DeleteMapping("/colleges/{id}")
    public void deleteCollege(@PathVariable("id") int idCollege) {
        collegeService.deleteCollege(idCollege);
    }


/*
                  DEPARTMENTS
 */

    @GetMapping("/colleges/{id}/departments")
    public List<Department> getCollegeDepartments(@PathVariable("id") int idCollege) {
        return collegeService.getCollegeDepartments(idCollege);
    }

    @PostMapping("/colleges/{id}/departments")
    public Map<String, String> addDepartmentToCollege(@PathVariable("id") int idCollege, @RequestBody @Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            FieldError fieldError = bindingResult.getFieldError();
            errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            return errors;
        } else {
            collegeService.addDepartmentToCollege(idCollege, department);
            return null;
        }

    }


    @PutMapping("/departments/{idDepartment}")
    public void updateDepartment(@PathVariable("idDepartment") int idDepartment, @RequestBody Department department) {
        collegeService.updateDepartment(idDepartment, department);
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable("id") int idDepartment) {
        collegeService.deleteDepartment(idDepartment);
    }

/*
               PROFESSORS
 */

    @GetMapping("/colleges/{idCollege}/professors")
    public List<GetProfessorForCollege> getAllProfessorsForCollege(@PathVariable("idCollege") int idCollege) {
        return collegeService.getAllProfessorForCollege(idCollege);
    }

    @PostMapping("/departments/{idDep}/professors")
    public Map<String, String> addProfessorToDepartment(@PathVariable("idDep") int idDep, @RequestBody @Valid PostProfessorForCollege postProfessorForCollege, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            }
            return errors;
        } else {
            collegeService.addProfessorToDepartment(idDep, postProfessorForCollege);
            return null;
        }
    }

    @DeleteMapping("/professors/{id}")
    public void deleteProfessor(@PathVariable("id") int idProfessor) {
        collegeService.deleteProfessor(idProfessor);
    }
}
