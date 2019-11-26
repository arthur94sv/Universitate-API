package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.student.AddStudentToPromotion;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import com.example.API.Universitate.entities.lookUpTable.Repartition;
import com.example.API.Universitate.mapper.PromotionMapperImpl;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.PromotionRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PromotionServiceTestAddStudentToPromotion {
    PromotionRepository mockedPromotionRepository = mock(PromotionRepository.class);
    PromotionEntity promotionEntity = new PromotionEntity();


    PromotionMapperImpl promotionMapper = new PromotionMapperImpl();
    StudentMapperImpl studentMapper = new StudentMapperImpl();

    PromotionService promotionService = new PromotionService(mockedPromotionRepository, promotionMapper, studentMapper);

    AddStudentToPromotion addStudentToPromotionBuget = new AddStudentToPromotion();
    Repartition repartitionBuget = new Repartition();

    AddStudentToPromotion addStudentToPromotionTaxa = new AddStudentToPromotion();
    Repartition repartitionTaxa = new Repartition();



    @Before
    public void setUp() throws Exception {
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


        repartitionBuget.setId(1);
        repartitionBuget.setRepartition("licenta");
        addStudentToPromotionBuget.nume = "Popescu";
        addStudentToPromotionBuget.prenume = "Ion";
        addStudentToPromotionBuget.repartition = repartitionBuget;


        repartitionTaxa.setId(2);
        repartitionTaxa.setRepartition("taxa");
        addStudentToPromotionTaxa.nume = "Grigorescu";
        addStudentToPromotionTaxa.prenume = "Mihai";
        addStudentToPromotionTaxa.repartition = repartitionTaxa;


    }

    /*
               promotion.nrLocuriBuget = 100 > nrStudentsBuget = 99
     */
    @Test
    public void addStudentToPromotionBugetWhenNrLocuriBugetMaiMareNrStudentsBuget() {
        long nrStudentsBuget = 99;

        when(mockedPromotionRepository.getPromotionById(1)).thenReturn(promotionEntity);
        when(mockedPromotionRepository.countNrStudentsBuget(1)).thenReturn(nrStudentsBuget);


        StudentEntity studentEntity = studentMapper.toEntityFromAddStudentToPromotion(addStudentToPromotionBuget);

        promotionService.addStudentToPromotion(1, addStudentToPromotionBuget);

        verify(mockedPromotionRepository).addStudentToPromotion(1, studentEntity, addStudentToPromotionBuget.repartition);
    }

    /*
               promotion.nrLocuriBuget = 100 == nrStudentsBuget = 100
     */

    @Test(expected = RuntimeException.class)
    public void addStudentToPromotionBugetWhenNrLocuriBugetEgalNrStudentsBuget() {
        long nrStudentsBuget = 100;

        when(mockedPromotionRepository.getPromotionById(1)).thenReturn(promotionEntity);
        when(mockedPromotionRepository.countNrStudentsBuget(1)).thenReturn(nrStudentsBuget);

        promotionService.addStudentToPromotion(1, addStudentToPromotionBuget);
    }


    /*
              promotion.nrLocuriTaxa = 50 > nrStudentsTaxa = 49
    */
    @Test
    public void addStudentToPromotionTaxaWhenNrLocuriTaxaMaiMareNrStudentsTaxa() {
        long nrStudentsTaxa = 49;

        when(mockedPromotionRepository.getPromotionById(1)).thenReturn(promotionEntity);
        when(mockedPromotionRepository.countNrStudentsTaxa(1)).thenReturn(nrStudentsTaxa);


        StudentEntity studentEntity = studentMapper.toEntityFromAddStudentToPromotion(addStudentToPromotionTaxa);

        promotionService.addStudentToPromotion(1, addStudentToPromotionTaxa);

        verify(mockedPromotionRepository).addStudentToPromotion(1, studentEntity, addStudentToPromotionTaxa.repartition);
    }

        /*
               promotion.nrLocuriBuget = 50 == nrStudentsBuget = 50
     */

    @Test(expected = RuntimeException.class)
    public void addStudentToPromotionTaxaWhenNrLocuriTaxaEgalNrStudentsTaxa() {
        long nrStudentsTaxa = 50;

        when(mockedPromotionRepository.getPromotionById(1)).thenReturn(promotionEntity);
        when(mockedPromotionRepository.countNrStudentsTaxa(1)).thenReturn(nrStudentsTaxa);

        promotionService.addStudentToPromotion(1, addStudentToPromotionTaxa);
    }
}
