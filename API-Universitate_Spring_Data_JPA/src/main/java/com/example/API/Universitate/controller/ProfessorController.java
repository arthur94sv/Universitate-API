package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.professor.UpdateProfessorDTO;
import com.example.API.Universitate.dto.professor.CreateProfessorDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorForCollegeDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorDTO;
import com.example.API.Universitate.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
public class ProfessorController {
    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/colleges/{id}/professors")
    public List<DisplayProfessorForCollegeDTO> getAllProfessorsForCollege(@PathVariable("id") int idCollege) {
        return professorService.getAllProfessorForCollege(idCollege);
    }

    @GetMapping("/professors")
    public List<DisplayProfessorDTO> searchProfessor(@RequestParam(name = "nume", required = true) String nume,
                                                     @RequestParam(name = "prenume", required = false) String prenume) {
        return professorService.searchProfessor(nume, prenume);
    }

    @PutMapping("/professors/{id}")
    public ResponseEntity updateProfessor(@PathVariable("id") int idProfessor,
                                          @Valid @RequestBody UpdateProfessorDTO updateProfessorDTO) {
        professorService.updateProfessor(idProfessor, updateProfessorDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/departments/{id}/professors")
    public ResponseEntity addProfessor(@PathVariable("id") int idDepartment,
                                       @RequestBody @Valid CreateProfessorDTO createProfessorDTO) {
        professorService.addProfessor(idDepartment, createProfessorDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @DeleteMapping("/professors/{id}")
    public ResponseEntity deleteProfessor(@PathVariable("id") int idProfessor) {
        professorService.deleteProfessor(idProfessor);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
