package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.student.GetStudent;
import com.example.API.Universitate.dto.student.PutStudent;
import com.example.API.Universitate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<GetStudent> searchStudent(@RequestParam(value = "nume", required = true) String nume,
                                          @RequestParam(value = "prenume", required = false) String prenume) {
        return studentService.searchStudent(nume, prenume);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int idStudent){
        studentService.deleteStudent(idStudent);
    }

    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable("id") int idStudent, @RequestBody PutStudent putStudent){
        studentService.updateStudent(idStudent, putStudent);
    }
}
