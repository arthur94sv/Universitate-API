package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.promotion.GetPromotion;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import com.example.API.Universitate.mapper.PromotionMapperImpl;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.PromotionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PromotionServiceTest {
    PromotionRepository mockedPromotionRepository = mock(PromotionRepository.class);
    List<PromotionEntity> listReturnedByRepository = new ArrayList<>();

    PromotionMapperImpl promotionMapper = new PromotionMapperImpl();
    StudentMapperImpl studentMapper = new StudentMapperImpl();

    List<GetPromotion> listExpected = new ArrayList<>();



    @Before
    public void setUp() throws Exception {
        PromotionEntity promotionEntity = new PromotionEntity();

        EducationalOfferEntity educationalOfferEntity = new EducationalOfferEntity();
        educationalOfferEntity.setId(1);
        educationalOfferEntity.setName("Informatica");

        CollegeEntity collegeEntity = new CollegeEntity();
        collegeEntity.setId(1);
        collegeEntity.setNume("Facultatea de Informatica");

        educationalOfferEntity.setCollegeEntity(collegeEntity);

        EducationLevel educationLevel = new EducationLevel();
        educationLevel.setId(1);
        educationLevel.setEducationLevel("licenta");
        educationalOfferEntity.setEducationLevel(educationLevel);

        promotionEntity.setId(1);
        promotionEntity.setEducationalOfferEntity(educationalOfferEntity);
        promotionEntity.setNrLocuriBuget(100);
        promotionEntity.setNrLocuriTaxa(50);
        promotionEntity.setStartYear(2015);
        promotionEntity.setEndYear(2018);

        listReturnedByRepository.add(promotionEntity);


        GetPromotion promotion = new GetPromotion();
        promotion.id = 1;
        promotion.educationalOffer = "Informatica";
        promotion.educationalLevel = "licenta";
        promotion.nrLocuriBuget = 100;
        promotion.nrLocuriTaxa = 50;
        promotion.startYear = 2015;
        promotion.endYear = 2018;

        listExpected.add(promotion);

    }

    @Test
    public void getPromotionsForCollegeWhenEducationalLevelNotNullAndEndYearNull() {
        when(mockedPromotionRepository.getPromotionsForCollegeAndEducationLevel(1, 1)).thenReturn(listReturnedByRepository);

        PromotionService promotionService = new PromotionService(mockedPromotionRepository, promotionMapper, studentMapper);

        List<GetPromotion> listReturnedByService = promotionService.getPromotionsForCollege(1, 1, null);

        assertEquals(listExpected, listReturnedByService);
    }

    @Test
    public void getPromotionsForCollegeWhenEducationalLevelAndEndYearNotNull() {
        when(mockedPromotionRepository.getPromotionsForCollegeEducationLevelAndEndYear(1, 1, 2018)).thenReturn(listReturnedByRepository);

        PromotionService promotionService = new PromotionService(mockedPromotionRepository, promotionMapper, studentMapper);

        List<GetPromotion> listReturnedByService = promotionService.getPromotionsForCollege(1, 1, 2018);

        assertEquals(listExpected, listReturnedByService);
    }

    @Test
    public void getPromotionsForCollegeWhenEducationalLevelAndEndYearNull() {
        when(mockedPromotionRepository.getPromotionsForCollege(1)).thenReturn(listReturnedByRepository);

        PromotionService promotionService = new PromotionService(mockedPromotionRepository, promotionMapper, studentMapper);

        List<GetPromotion> listReturnedByService = promotionService.getPromotionsForCollege(1, null, null);

        assertEquals(listExpected, listReturnedByService);
    }

}
