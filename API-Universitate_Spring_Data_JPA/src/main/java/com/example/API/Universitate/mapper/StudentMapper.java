package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.student.*;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    DisplayStudentForPromotionDTO toDisplayStudentForPromotionDTO(StudentEntity studentEntity);

    @Mapping(target = "promotionStudentEntities", ignore = true)
    StudentEntity toEntityFromAddStudentToPromotionDTO(AddStudentToPromotionDTO addStudentToPromotionDTO);

    @Mapping(source = "promotionStudentEntities", target = "listOfPromotions")
    DisplayStudentDTO toDisplayStudentDTO(StudentEntity studentEntity);

    default List<DisplayPromotionsForStudentDTO> toDisplayStudentForPromotionDTO(List<PromotionStudentEntity> promotionStudentEntities) {
        if (promotionStudentEntities.isEmpty()) {
            return null;
        }

        List<DisplayPromotionsForStudentDTO> listOfPromotions = new ArrayList<>();

        for (PromotionStudentEntity ps : promotionStudentEntities) {
            DisplayPromotionsForStudentDTO displayPromotionsForStudentDTO = new DisplayPromotionsForStudentDTO();

            displayPromotionsForStudentDTO.college = ps.getPromotionEntity().getEducationalOfferEntity().getCollegeEntity().getNume();
            displayPromotionsForStudentDTO.educationalOffer = ps.getPromotionEntity().getEducationalOfferEntity().getName();
            displayPromotionsForStudentDTO.educationalLevel = ps.getPromotionEntity().getEducationalOfferEntity().getEducationLevel().getEducationLevel();
            displayPromotionsForStudentDTO.repartition = ps.getRepartition().getRepartition();
            displayPromotionsForStudentDTO.startYear = ps.getPromotionEntity().getStartYear();
            displayPromotionsForStudentDTO.endYear = ps.getPromotionEntity().getEndYear();

            listOfPromotions.add(displayPromotionsForStudentDTO);

        }

        return listOfPromotions;
    }
}
