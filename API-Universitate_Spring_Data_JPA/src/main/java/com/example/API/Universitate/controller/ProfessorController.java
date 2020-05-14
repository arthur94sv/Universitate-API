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
    public ResponseEntity<List<DisplayProfessorForCollegeDTO>> getAllProfessorsForCollege(@PathVariable("id") int idCollege) {
        List<DisplayProfessorForCollegeDTO> professorsList = professorService.getAllProfessorForCollege(idCollege);
        return new ResponseEntity<>(professorsList, HttpStatus.OK);
    }

    @GetMapping("/professors")
    public ResponseEntity<List<DisplayProfessorDTO>> searchProfessor(@RequestParam(name = "nume", required = true) String nume,
                                                                     @RequestParam(name = "prenume", required = false) String prenume) {
        List<DisplayProfessorDTO> professorsList = professorService.searchProfessor(nume, prenume);
        return new ResponseEntity<>(professorsList, HttpStatus.OK);
    }

    @PostMapping("/departments/{id}/professors")
    public ResponseEntity<Void> addProfessor(@PathVariable("id") int idDepartment,
                                             @RequestBody @Valid CreateProfessorDTO createProfessorDTO) {
        professorService.addProfessor(idDepartment, createProfessorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/professors/{id}")
    public ResponseEntity<Void> updateProfessor(@PathVariable("id") int idProfessor,
                                                @Valid @RequestBody UpdateProfessorDTO updateProfessorDTO) {
        professorService.updateProfessor(idProfessor, updateProfessorDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/professors/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") int idProfessor) {
        professorService.deleteProfessor(idProfessor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
