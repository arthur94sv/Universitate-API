package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.promotion.PromotionForEducOffer;
import com.example.API.Universitate.dto.educationalOffer.GetEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PostEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PutEducationalOffer;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.mapper.EducationalOfferMapper;
import com.example.API.Universitate.mapper.EducationalOfferMapperImpl;
import com.example.API.Universitate.mapper.PromotionMapperImpl;
import com.example.API.Universitate.repository.EducationalOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EducationalOfferService {
    private EducationalOfferRepository educationalOfferRepository;
    private EducationalOfferMapper educationalOfferMapper;
    private PromotionMapperImpl promotionMapper;

    @Autowired
    public EducationalOfferService(EducationalOfferRepository educationalOfferRepository,
                                   EducationalOfferMapperImpl educationalOfferMapper,
                                   PromotionMapperImpl promotionMapper) {
        this.educationalOfferRepository = educationalOfferRepository;
        this.educationalOfferMapper = educationalOfferMapper;
        this.promotionMapper = promotionMapper;
    }

    public List<GetEducationalOffer> getEducationalOffersForCollege(int idCollege){
        List<EducationalOfferEntity> listOfEducationOfferEntities = educationalOfferRepository.getEducationalOffersForCollege(idCollege);
        List<GetEducationalOffer> listOfEducationalOffers = listOfEducationOfferEntities.stream()
                .map(educOfEntity -> educationalOfferMapper.toDTO(educOfEntity))
                .collect(Collectors.toList());

        return listOfEducationalOffers;
    }

    public void addEducationalOffer(int idCollege, PostEducationalOffer postEducationalOffer) {
        EducationalOfferEntity educationalOfferEntity = educationalOfferMapper.toEntityPostRequest(postEducationalOffer);
        educationalOfferRepository.addEducationalOffer(idCollege, educationalOfferEntity);
    }

    public void updateEducationalOffer(int id, PutEducationalOffer putEducationalOffer){
        EducationalOfferEntity educationalOfferEntity = educationalOfferMapper.toEntityPutRequest(putEducationalOffer);
        educationalOfferRepository.updateEducationalOffer(id, educationalOfferEntity);
    }

/*
                            PROMOTIONS
 */

    public void addPromotionToEducOffer(int idEducOffer, PromotionForEducOffer promotionForEducOffer){
        PromotionEntity promotionEntity = promotionMapper.toEntity(promotionForEducOffer);
        educationalOfferRepository.addPromotionToEducOffer(idEducOffer, promotionEntity);
    }

    public List<PromotionForEducOffer> getPromotionsForEducationalOffer(int idEducOffer){
        List<PromotionEntity> listOfPromotionEntities = educationalOfferRepository.getPromotionsForEducationalOffer(idEducOffer);
        List<PromotionForEducOffer> listOfPromotions = listOfPromotionEntities.stream()
                .map(promotionEntity -> promotionMapper.toDTO(promotionEntity))
                .collect(Collectors.toList());

        return listOfPromotions;
    }
}
