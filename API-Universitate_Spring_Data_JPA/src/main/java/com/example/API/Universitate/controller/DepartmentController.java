package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.department.CreateDepartmentDTO;
import com.example.API.Universitate.dto.department.DisplayDepartmentDTO;
import com.example.API.Universitate.dto.department.UpdateDepartmentDTO;
import com.example.API.Universitate.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/colleges/{id}/departments")
    public ResponseEntity<List<DisplayDepartmentDTO>> getCollegeDepartments(@PathVariable("id") int idCollege) {
        List<DisplayDepartmentDTO> departmentsList = departmentService.getDepartmentsForColleges(idCollege);
        return new ResponseEntity<>(departmentsList, HttpStatus.OK);
    }

    @PostMapping("/colleges/{id}/departments")
    public ResponseEntity<Void> addDepartmentToCollege(@PathVariable("id") int idCollege,
                                                       @RequestBody @Valid CreateDepartmentDTO createDepartmentDTO) {
        departmentService.addDepartmentToCollege(idCollege, createDepartmentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable("id") int idDepartment,
                                                 @RequestBody @Valid UpdateDepartmentDTO updateDepartmentDTO) {
        departmentService.updateDepartment(idDepartment, updateDepartmentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") int idDepartment) {
        departmentService.deleteDepartment(idDepartment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
