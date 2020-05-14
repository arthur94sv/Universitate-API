package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.student.AddStudentToPromotionDTO;
import com.example.API.Universitate.dto.student.DisplayStudentDTO;
import com.example.API.Universitate.dto.student.DisplayStudentForPromotionDTO;
import com.example.API.Universitate.dto.student.UpdateStudentDTO;
import com.example.API.Universitate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<DisplayStudentDTO>> searchStudent(@RequestParam(value = "nume", required = true) String nume,
                                                 @RequestParam(value = "prenume", required = false) String prenume) {
        List<DisplayStudentDTO> displayStudentDTOList = studentService.searchStudent(nume, prenume);
        return new ResponseEntity<>(displayStudentDTOList, HttpStatus.OK);
    }

    @GetMapping("/promotions/{id}/students")
    public ResponseEntity<List<DisplayStudentForPromotionDTO>> getStudentsForPromotion(@PathVariable("id") int idPromotion) {
        List<DisplayStudentForPromotionDTO> displayStudentForPromotionDTOList = studentService.getStudentsForPromotion(idPromotion);
        return new ResponseEntity<>(displayStudentForPromotionDTOList, HttpStatus.OK);
    }

    @PostMapping("/promotions/{id}/students")
    public ResponseEntity<Void> addStudentToPromotion(@PathVariable("id") int idPromotion,
                                                      @RequestBody @Valid AddStudentToPromotionDTO addStudentToPromotionDTO) {
        studentService.addStudentToPromotion(idPromotion, addStudentToPromotionDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable("id") int idStudent,
                                        @RequestBody @Valid UpdateStudentDTO updateStudentDTO) {
        studentService.updateStudent(idStudent, updateStudentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int idStudent) {
        studentService.deleteStudent(idStudent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/promotions/{idPromotion}/students/{idStudent}")
    public ResponseEntity<Void> deleteStudentForPromotion(@PathVariable("idPromotion") int idPromotion, @PathVariable("idStudent") int idStudent) {
        studentService.deleteStudentFromPromotion(idPromotion, idStudent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

