package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.educationalOffer.CreateEducationalOfferDTO;
import com.example.API.Universitate.dto.educationalOffer.DisplayEducationalOfferDTO;
import com.example.API.Universitate.dto.educationalOffer.UpdateEducationalOfferDTO;
import com.example.API.Universitate.service.EducationalOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
public class EducationalOfferController {
    private EducationalOfferService educationalOfferService;

    @Autowired
    public EducationalOfferController(EducationalOfferService educationalOfferService) {
        this.educationalOfferService = educationalOfferService;
    }

    @GetMapping("/colleges/{id}/educationalOffers")
    public ResponseEntity<List<DisplayEducationalOfferDTO>> getEducationalOffersForCollege(@PathVariable("id") int idCollege) {
        List<DisplayEducationalOfferDTO> displayEducationalOfferDTOList = educationalOfferService.getEducationalOffersForCollege(idCollege);
        return new ResponseEntity<>(displayEducationalOfferDTOList, HttpStatus.OK);
    }

    @PostMapping("/colleges/{id}/educationalOffers")
    public ResponseEntity<Void> addEducationalOffer(@PathVariable("id") int idCollege,
                                                    @RequestBody @Valid CreateEducationalOfferDTO createEducationalOfferDTO) {
        educationalOfferService.addEducationalOffer(idCollege, createEducationalOfferDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/educationalOffers/{id}")
    public ResponseEntity<Void> updateEducationalOffer(@PathVariable("id") int idEducOffer,
                                                       @RequestBody @Valid UpdateEducationalOfferDTO updateEducationalOfferDTO) {
        educationalOfferService.updateEducationalOffer(idEducOffer, updateEducationalOfferDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
