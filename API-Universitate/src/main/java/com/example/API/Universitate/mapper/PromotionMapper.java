package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.promotion.GetPromotion;
import com.example.API.Universitate.dto.promotion.PromotionForEducOffer;
import com.example.API.Universitate.dto.promotion.PutPromotion;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    PromotionForEducOffer toDTO(PromotionEntity promotionEntity);

    @Mapping(target = "educationalOfferEntity", ignore = true)
    @Mapping(target = "promotionStudentEntity", ignore = true)
    PromotionEntity toEntity(PromotionForEducOffer promotionForEducOffer);

    @Mapping(source = "educationalOfferEntity", target = "educationalOffer")
    @Mapping(source = "educationalOfferEntity.educationLevel", target = "educationalLevel")
    GetPromotion toDTOPromotion(PromotionEntity promotionEntity);

    default String toStringEducationalOffer(EducationalOfferEntity educationalOfferEntity) {
        return educationalOfferEntity.getName();
    }

    default String toStringEducationalLevel(EducationLevel educationLevel) {
        return educationLevel.getEducationLevel();
    }

    @Mapping(target = "educationalOfferEntity", ignore = true)
    @Mapping(target = "promotionStudentEntity", ignore = true)
    PromotionEntity toEntityFromPutRequest(PutPromotion putPromotion);
}
