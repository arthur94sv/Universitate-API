package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.promotion.CreatePromotionDTO;
import com.example.API.Universitate.dto.promotion.DisplayPromotionDTO;
import com.example.API.Universitate.dto.promotion.UpdatePromotionDTO;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    CreatePromotionDTO toCreatePromotionDTO(PromotionEntity promotionEntity);

    @Mapping(target = "educationalOfferEntity", ignore = true)
    @Mapping(target = "promotionStudentEntity", ignore = true)
    PromotionEntity toEntity(CreatePromotionDTO createPromotionDTO);

    @Mapping(source = "educationalOfferEntity", target = "educationalOffer")
    @Mapping(source = "educationalOfferEntity.educationLevel", target = "educationalLevel")
    DisplayPromotionDTO toDisplayPromotionDTO(PromotionEntity promotionEntity);

    default String toStringEducationalOffer(EducationalOfferEntity educationalOfferEntity) {
        return educationalOfferEntity.getName();
    }

    default String toStringEducationalLevel(EducationLevel educationLevel) {
        return educationLevel.getEducationLevel();
    }

    @Mapping(target = "educationalOfferEntity", ignore = true)
    @Mapping(target = "promotionStudentEntity", ignore = true)
    PromotionEntity toEntityFromPutRequest(UpdatePromotionDTO updatePromotionDTO);
}
