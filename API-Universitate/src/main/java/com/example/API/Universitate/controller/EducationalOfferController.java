package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.promotion.PromotionForEducOffer;
import com.example.API.Universitate.dto.educationalOffer.GetEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PostEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PutEducationalOffer;
import com.example.API.Universitate.service.EducationalOfferService;
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
public class EducationalOfferController {
    private EducationalOfferService educationalOfferService;

    @Autowired
    public EducationalOfferController(EducationalOfferService educationalOfferService) {
        this.educationalOfferService = educationalOfferService;
    }

    @GetMapping("/colleges/{id}/educationalOffers")
    public List<GetEducationalOffer> getEducationalOffersForCollege(@PathVariable("id") int idCollege) {
        return educationalOfferService.getEducationalOffersForCollege(idCollege);
    }

    @PostMapping("/colleges/{id}/educationalOffers")
    public Map<String, String> addEducationalOffer(@PathVariable("id") int idCollege, @RequestBody @Valid PostEducationalOffer postEducationalOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            }
            return errors;
        } else {
            educationalOfferService.addEducationalOffer(idCollege, postEducationalOffer);
            return null;
        }

    }

    @PutMapping("/educationalOffers/{id}")
    public void updateEducationalOffer(@PathVariable("id") int id, @RequestBody PutEducationalOffer putEducationalOffer) {
        educationalOfferService.updateEducationalOffer(id, putEducationalOffer);
    }

/*
                         PROMOTIONS
 */

    @PostMapping("/educationalOffers/{id}/promotions")
    public Map<String, String> addPromotionToEducOffer(@PathVariable("id") int idEducOffer, @RequestBody @Valid PromotionForEducOffer promotionForEducOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField().toString(), fieldError.getDefaultMessage());
            }
            return errors;
        } else {
            educationalOfferService.addPromotionToEducOffer(idEducOffer, promotionForEducOffer);
            return null;
        }

    }

    @GetMapping("/educationalOffers/{id}/promotions")
    public List<PromotionForEducOffer> getPromotionsForEducationalOffer(@PathVariable("id") int idEducOffer) {
        return educationalOfferService.getPromotionsForEducationalOffer(idEducOffer);
    }
}
