package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.EducationalOfferEntity;
import com.example.API.Universitate.entities.PromotionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EducationalOfferRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EducationalOfferEntity> getEducationalOffersForCollege(int idCollege) {
        String jpql = "FROM EducationalOfferEntity eo " +
                "INNER JOIN FETCH eo.educationLevel " +
                "WHERE eo.collegeEntity.id = :idCollege " +
                "ORDER BY eo.educationLevel.id, eo.name";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("idCollege", idCollege);

        List<EducationalOfferEntity> listOfEducationalOfferEntities = query.getResultList();
        return listOfEducationalOfferEntities;
    }

    public void addEducationalOffer(int idCollege, EducationalOfferEntity educationalOfferEntity) {
        CollegeEntity collegeEntity = entityManager.find(CollegeEntity.class, idCollege);
        educationalOfferEntity.setCollegeEntity(collegeEntity);
        entityManager.persist(educationalOfferEntity);
    }

    public void updateEducationalOffer(int id, EducationalOfferEntity educationalOfferEntity) {
        EducationalOfferEntity oldEducationalOfferEntity = entityManager.find(EducationalOfferEntity.class, id);
        oldEducationalOfferEntity.setName(educationalOfferEntity.getName());
    }

/*
                            PROMOTIONS
 */

    public void addPromotionToEducOffer(int idEducOffer, PromotionEntity promotionEntity) {
        EducationalOfferEntity educationalOfferEntity = entityManager.find(EducationalOfferEntity.class, idEducOffer);
        promotionEntity.setEducationalOfferEntity(educationalOfferEntity);
        entityManager.persist(promotionEntity);
    }

    public List<PromotionEntity> getPromotionsForEducationalOffer(int idEducOffer) {
        String jpql = "FROM PromotionEntity p " +
                "INNER JOIN FETCH p.educationalOfferEntity eo " +
                "WHERE eo.id = :id " +
                "ORDER BY p.startYear";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idEducOffer);

        List<PromotionEntity> listOfPromotionEntities = query.getResultList();
        return listOfPromotionEntities;
    }
}
