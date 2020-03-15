package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.educationalOffer.DisplayEducationalOfferDTO;
import com.example.API.Universitate.dto.educationalOffer.CreateEducationalOfferDTO;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationalOfferMapper {


    @Mapping(target = "educationLevel", source = "educationLevel")
    DisplayEducationalOfferDTO toDisplayEducationalOfferDTO(EducationalOfferEntity educationalOfferEntity);

    @Mapping(target = "collegeEntity", ignore = true)
    @Mapping(target = "listOfPromotionEntities", ignore = true)
    EducationalOfferEntity toEntityFromCreateEducationalOfferDTO(CreateEducationalOfferDTO createEducationalOfferDTO);

    default String toString(EducationLevel educationLevel) {
        return educationLevel.getEducationLevel();
    }
}
