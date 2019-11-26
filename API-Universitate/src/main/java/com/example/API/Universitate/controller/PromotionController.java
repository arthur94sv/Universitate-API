package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.student.AddStudentToPromotion;
import com.example.API.Universitate.dto.student.StudentForPromotion;
import com.example.API.Universitate.dto.promotion.GetPromotion;
import com.example.API.Universitate.dto.promotion.PutPromotion;
import com.example.API.Universitate.service.PromotionService;
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
public class PromotionController {
    private PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("colleges/{id}/promotions")
    public List<GetPromotion> getPromotionsForCollege(@PathVariable("id") int idCollege,
                                                      @RequestParam(name = "idEducLevel", required = false) Integer idEducLevel,
                                                      @RequestParam(name = "endYear", required = false) Integer endYear) {
        return promotionService.getPromotionsForCollege(idCollege, idEducLevel, endYear);
    }

    @PutMapping("/promotions/{id}")
    public void updatePromotion(@PathVariable("id") int idPromotion, @RequestBody PutPromotion putPromotion) {
        promotionService.updatePromotion(idPromotion, putPromotion);
    }

    /*
                            STUDENTS
     */
    @GetMapping("/promotions/{id}/students")
    public List<StudentForPromotion> getStudentsForPromotion(@PathVariable("id") int idPromotion) {
        return promotionService.getStudentsForPromotion(idPromotion);
    }


    @PostMapping("/promotions/{id}/students")
    public Map<String, String> addStudentToPromotion(@PathVariable("id") int idPromotion, @RequestBody @Valid AddStudentToPromotion addStudentToPromotion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            }
            return errors;
        } else {
            promotionService.addStudentToPromotion(idPromotion, addStudentToPromotion);
            return null;
        }
    }

    @DeleteMapping("/promotions/{idPromotion}/students/{idStudent}")
    public void deleteStudentForPromotion(@PathVariable("idPromotion") int idPromotion, @PathVariable("idStudent") int idStudent) {
        promotionService.deleteStudentFromPromotion(idPromotion, idStudent);
    }

}
