package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.course.CreateCourseDTO;
import com.example.API.Universitate.dto.course.DisplayCourseDTO;
import com.example.API.Universitate.dto.course.UpdateCourseDTO;
import com.example.API.Universitate.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/professors/{id}/courses")
    public ResponseEntity<List<DisplayCourseDTO>> getAllCoursesForProfessor(@PathVariable("id") int idProfessor) {
        List<DisplayCourseDTO> coursesList = courseService.getAllCoursesForProfessor(idProfessor);
        return new ResponseEntity<>(coursesList, HttpStatus.OK);
    }

    @PostMapping("/professors/{id}/courses")
    public ResponseEntity<Void> addCourse(@PathVariable("id") int idProfessor,
                                          @RequestBody @Valid CreateCourseDTO createCourseDTO) {
        courseService.addCourse(idProfessor, createCourseDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable("id") int idCourse,
                                             @RequestBody @Valid UpdateCourseDTO updateCourseDTO) {
        courseService.updateCourse(idCourse, updateCourseDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") int idCourse) {
        courseService.deleteCourse(idCourse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
