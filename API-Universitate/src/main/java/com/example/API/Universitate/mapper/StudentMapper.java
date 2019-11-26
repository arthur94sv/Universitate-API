package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.student.AddStudentToPromotion;
import com.example.API.Universitate.dto.student.GetPromotionStudent;
import com.example.API.Universitate.dto.student.GetStudent;
import com.example.API.Universitate.dto.student.PutStudent;
import com.example.API.Universitate.dto.student.StudentForPromotion;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentForPromotion toDTO(StudentEntity studentEntity);

    @Mapping(target = "promotionStudentEntities", ignore = true)
    StudentEntity toEntityFromAddStudentToPromotion(AddStudentToPromotion addStudentToPromotion);

    @Mapping(source = "promotionStudentEntities", target = "listOfPromotions")
    GetStudent toDTOforStudent(StudentEntity studentEntity);

    default List<GetPromotionStudent> toDTO(List<PromotionStudentEntity> promotionStudentEntities) {
        if (promotionStudentEntities.isEmpty()) {
            return null;
        }

        List<GetPromotionStudent> listOfPromotions = new ArrayList<>();

        for (PromotionStudentEntity ps : promotionStudentEntities) {
            GetPromotionStudent getPromotionStudent = new GetPromotionStudent();

            getPromotionStudent.college = ps.getPromotionEntity().getEducationalOfferEntity().getCollegeEntity().getNume();
            getPromotionStudent.educationalOffer = ps.getPromotionEntity().getEducationalOfferEntity().getName();
            getPromotionStudent.educationalLevel = ps.getPromotionEntity().getEducationalOfferEntity().getEducationLevel().getEducationLevel();
            getPromotionStudent.repartition = ps.getRepartition().getRepartition();
            getPromotionStudent.startYear = ps.getPromotionEntity().getStartYear();
            getPromotionStudent.endYear = ps.getPromotionEntity().getEndYear();

            listOfPromotions.add(getPromotionStudent);

        }

        return listOfPromotions;
    }

    @Mapping(target = "promotionStudentEntities", ignore = true)
    StudentEntity toEntityFromPutStudent(PutStudent putStudent);
}
