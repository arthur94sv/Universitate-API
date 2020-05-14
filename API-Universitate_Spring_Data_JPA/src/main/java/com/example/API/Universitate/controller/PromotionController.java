package com.example.API.Universitate.controller;

import com.example.API.Universitate.dto.promotion.CreatePromotionDTO;
import com.example.API.Universitate.dto.promotion.DisplayPromotionDTO;
import com.example.API.Universitate.dto.promotion.UpdatePromotionDTO;
import com.example.API.Universitate.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
public class PromotionController {
    private PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/educationalOffers/{id}/promotions")
    public ResponseEntity<List<DisplayPromotionDTO>> getPromotionsForEducationalOffer(@PathVariable("id") int idEducOffer) {
        List<DisplayPromotionDTO> displayPromotionDTOList = promotionService.getPromotionsForEducationalOffer(idEducOffer);
        return new ResponseEntity<>(displayPromotionDTOList, HttpStatus.OK);
    }

    @GetMapping("colleges/{id}/promotions")
    public ResponseEntity<List<DisplayPromotionDTO>> getPromotionsForCollege(@PathVariable("id") int idCollege,
                                                                             @RequestParam(name = "idEducLevel", required = false) Integer idEducLevel,
                                                                             @RequestParam(name = "endYear", required = false) Integer endYear) {
        List<DisplayPromotionDTO> displayPromotionDTOList = promotionService.getPromotionsForCollege(idCollege, idEducLevel, endYear);
        return new ResponseEntity<>(displayPromotionDTOList, HttpStatus.OK);
    }

    @PostMapping("/educationalOffers/{id}/promotions")
    public ResponseEntity<Void> addPromotionToEducOffer(@PathVariable("id") int idEducOffer,
                                                  @RequestBody @Valid CreatePromotionDTO createPromotionDTO) {
        promotionService.addPromotionToEducOffer(idEducOffer, createPromotionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<Void> updatePromotion(@PathVariable("id") int idPromotion,
                                          @RequestBody @Valid UpdatePromotionDTO updatePromotionDTO) {
        promotionService.updatePromotion(idPromotion, updatePromotionDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
