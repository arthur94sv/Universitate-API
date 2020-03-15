package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {

    @Query("FROM PromotionEntity p " +
            "INNER JOIN FETCH p.educationalOfferEntity eo " +
            "WHERE eo.id = ?1 " +
            "ORDER BY p.startYear")
    List<PromotionEntity> getAllPromotionsForEducationalOffer(int idEducOffer);


    @Query("FROM PromotionEntity p " +
            "INNER JOIN FETCH p.educationalOfferEntity eo " +
            "WHERE eo.collegeEntity.id = ?1 " +
            "ORDER BY p.startYear, eo.name")
    List<PromotionEntity> getPromotionsByCollege(int idCollege);

    @Query("FROM PromotionEntity p " +
            "INNER JOIN FETCH p.educationalOfferEntity eo " +
            "WHERE eo.collegeEntity.id = ?1 AND eo.educationLevel.id = ?2 " +
            "ORDER BY p.startYear, eo.name")
    List<PromotionEntity> getPromotionsByCollegeAndEducationLevel(int idCollege, int idEducLevel);

    @Query("FROM PromotionEntity p " +
            "INNER JOIN FETCH p.educationalOfferEntity eo " +
            "WHERE eo.collegeEntity.id = ?1 AND eo.educationLevel.id = ?2 AND p.endYear = ?3 " +
            "ORDER BY p.startYear, eo.name")
    List<PromotionEntity> getPromotionsByCollege_EducationalLevel_And_EndYear(int idCollege, int idEducLevel, int endYear);

    /*
             Helper methods;  To be used in service for business logic
     */

    @Query("SELECT count(ps.studentEntity) " +
            "FROM PromotionStudentEntity ps " +
            "WHERE ps.promotionEntity.id = ?1 AND ps.repartition.id = 1")
    long countNrStudentsBuget(int idPromotion);

    @Query("SELECT count(ps.studentEntity) " +
            "FROM PromotionStudentEntity ps " +
            "WHERE ps.promotionEntity.id = ?1 AND ps.repartition.id = 2")
    long countNrStudentsTaxa(int idPromotion);
}
