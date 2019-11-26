package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.educationalOffer.GetEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PostEducationalOffer;
import com.example.API.Universitate.dto.educationalOffer.PutEducationalOffer;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationalOfferMapper {


    @Mapping(target = "educationLevel", source = "educationLevel")
    GetEducationalOffer toDTO(EducationalOfferEntity educationalOfferEntity);

    default String toString(EducationLevel educationLevel){
        return educationLevel.getEducationLevel();
    }

    @Mapping(target="collegeEntity", ignore = true)
    @Mapping(target="listOfPromotionEntities", ignore = true)
    EducationalOfferEntity toEntityPostRequest(PostEducationalOffer postEducationalOffer);

    @Mapping(target="collegeEntity", ignore = true)
    @Mapping(target="listOfPromotionEntities", ignore = true)
    EducationalOfferEntity toEntityPutRequest(PutEducationalOffer putEducationalOffer);
}
