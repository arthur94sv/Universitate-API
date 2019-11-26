package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.student.AddStudentToPromotion;
import com.example.API.Universitate.dto.student.StudentForPromotion;
import com.example.API.Universitate.dto.promotion.GetPromotion;
import com.example.API.Universitate.dto.promotion.PutPromotion;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.mapper.PromotionMapperImpl;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromotionService {
    private PromotionRepository promotionRepository;
    private PromotionMapperImpl promotionMapper;
    private StudentMapperImpl studentMapper;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, PromotionMapperImpl promotionMapper, StudentMapperImpl studentMapper) {
        this.promotionRepository = promotionRepository;
        this.promotionMapper = promotionMapper;
        this.studentMapper = studentMapper;
    }

    public List<GetPromotion> getPromotionsForCollege(int idCollege, Integer idEducLevel, Integer endYear) {
        if (idEducLevel != null && endYear == null) {
            List<PromotionEntity> listOfPromotionEntities = promotionRepository.getPromotionsForCollegeAndEducationLevel(idCollege, idEducLevel);
            List<GetPromotion> listOfGetPromotions = listOfPromotionEntities.stream()
                    .map(promotionEntity -> promotionMapper.toDTOPromotion(promotionEntity))
                    .collect(Collectors.toList());

            return listOfGetPromotions;
        } else if (idEducLevel != null && endYear != null) {
            List<PromotionEntity> listOfPromotionEntities = promotionRepository.getPromotionsForCollegeEducationLevelAndEndYear(idCollege, idEducLevel, endYear);
            List<GetPromotion> listOfGetPromotions = listOfPromotionEntities.stream()
                    .map(promotionEntity -> promotionMapper.toDTOPromotion(promotionEntity))
                    .collect(Collectors.toList());

            return listOfGetPromotions;
        } else {
            List<PromotionEntity> listOfPromotionEntities = promotionRepository.getPromotionsForCollege(idCollege);
            List<GetPromotion> listOfGetPromotions = listOfPromotionEntities.stream()
                    .map(promotionEntity -> promotionMapper.toDTOPromotion(promotionEntity))
                    .collect(Collectors.toList());

            return listOfGetPromotions;
        }
    }

    public void updatePromotion(int idPromotion, PutPromotion putPromotion) {
        PromotionEntity promotionEntity = promotionMapper.toEntityFromPutRequest(putPromotion);
        promotionRepository.updatePromotion(idPromotion, promotionEntity);
    }

    /*
                          STUDENTS
     */
    public List<StudentForPromotion> getStudentsForPromotion(int idPromotion) {
        List<StudentEntity> listOfStudentEntities = promotionRepository.getStudentsForPromotion(idPromotion);
        List<StudentForPromotion> listOfStudents = listOfStudentEntities.stream()
                .map(studentEntity -> studentMapper.toDTO(studentEntity))
                .collect(Collectors.toList());

        return listOfStudents;
    }


    public void addStudentToPromotion(int idPromotion, AddStudentToPromotion addStudentToPromotion) {
        StudentEntity studentEntity = studentMapper.toEntityFromAddStudentToPromotion(addStudentToPromotion);

        PromotionEntity promotionEntity = promotionRepository.getPromotionById(idPromotion);


        switch (addStudentToPromotion.repartition.getId()) {
            // buget
            case 1:
                if ((long) promotionEntity.getNrLocuriBuget() > promotionRepository.countNrStudentsBuget(idPromotion)) {
                    promotionRepository.addStudentToPromotion(idPromotion, studentEntity, addStudentToPromotion.repartition);
                } else {
                    throw new RuntimeException("Nu mai exista locuri disponibile la buget");
                }
                break;
            // taxa
            case 2:
                if ((long) promotionEntity.getNrLocuriTaxa() > promotionRepository.countNrStudentsTaxa(idPromotion)) {
                    promotionRepository.addStudentToPromotion(idPromotion, studentEntity, addStudentToPromotion.repartition);
                } else {
                    throw new RuntimeException("Nu mai exista locuri disponibile la taxa");
                }
                break;
        }


    }

    public void deleteStudentFromPromotion(int idPromotion, int idStudent){
        promotionRepository.deleteStudentFromPromotion(idPromotion, idStudent);
    }


}
