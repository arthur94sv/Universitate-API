package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.college.CreateCollegeDTO;
import com.example.API.Universitate.dto.college.DisplayCollegeDTO;
import com.example.API.Universitate.dto.college.UpdateCollegeDTO;
import com.example.API.Universitate.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class CollegeController {
    private CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("/colleges")
    public ResponseEntity<List<DisplayCollegeDTO>> getAllColleges() {
        List<DisplayCollegeDTO> collegesList = collegeService.getAllColleges();
        return new ResponseEntity<>(collegesList, HttpStatus.OK);
    }

    @PostMapping("/colleges")
    public ResponseEntity<Void> addCollege(@RequestBody @Valid CreateCollegeDTO createCollegeDTO) {
        collegeService.addCollege(createCollegeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/colleges/{id}")
    public ResponseEntity<Void> updateCollege(@PathVariable("id") int idColleges,
                                              @RequestBody @Valid UpdateCollegeDTO updateCollegeDTO) {
        collegeService.updateCollege(idColleges, updateCollegeDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/colleges/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable("id") int idCollege) {
        collegeService.deleteCollege(idCollege);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
