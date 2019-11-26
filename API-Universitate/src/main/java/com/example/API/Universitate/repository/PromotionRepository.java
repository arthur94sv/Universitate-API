package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import com.example.API.Universitate.entities.lookUpTable.Repartition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PromotionRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public List<PromotionEntity> getPromotionsForCollege(int idCollege) {
        String jpql = "FROM PromotionEntity p " +
                "INNER JOIN FETCH p.educationalOfferEntity eo " +
                "WHERE eo.collegeEntity.id = :id " +
                "ORDER BY p.startYear, eo.name";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idCollege);

        List<PromotionEntity> listOfPromotionEntities = query.getResultList();
        return listOfPromotionEntities;
    }

    public List<PromotionEntity> getPromotionsForCollegeAndEducationLevel(int idCollege, int idEducLevel) {
        String jpql = "FROM PromotionEntity p " +
                "INNER JOIN FETCH p.educationalOfferEntity eo " +
                "WHERE eo.collegeEntity.id = :idCollege AND eo.educationLevel.id = :idEducLevel " +
                "ORDER BY p.startYear, eo.name";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("idCollege", idCollege);
        query.setParameter("idEducLevel", idEducLevel);

        List<PromotionEntity> listOfPromotionEntities = query.getResultList();
        return listOfPromotionEntities;
    }

    public List<PromotionEntity> getPromotionsForCollegeEducationLevelAndEndYear(int idCollege, int idEducLevel, int endYear) {
        String jpql = "FROM PromotionEntity p " +
                "INNER JOIN FETCH p.educationalOfferEntity eo " +
                "WHERE eo.collegeEntity.id = :idCollege AND eo.educationLevel.id = :idEducLevel AND p.endYear =: endYear " +
                "ORDER BY p.startYear, eo.name";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("idCollege", idCollege);
        query.setParameter("idEducLevel", idEducLevel);
        query.setParameter("endYear", endYear);

        List<PromotionEntity> listOfPromotionEntities = query.getResultList();
        return listOfPromotionEntities;
    }

    public void updatePromotion(int idPromotion, PromotionEntity promotionEntity) {
        PromotionEntity oldPromotionEntity = entityManager.find(PromotionEntity.class, idPromotion);

        oldPromotionEntity.setNrLocuriBuget(promotionEntity.getNrLocuriBuget());
        oldPromotionEntity.setNrLocuriTaxa(promotionEntity.getNrLocuriTaxa());

    }

/*
                          STUDENTS
 */

    public List<StudentEntity> getStudentsForPromotion(int idPromotion) {
        String jpql = "FROM StudentEntity s " +
                "INNER JOIN FETCH s.promotionStudentEntities ps " +
                "INNER JOIN FETCH ps.promotionEntity p " +
                "INNER JOIN FETCH p.educationalOfferEntity eo " +
                "INNER JOIN FETCH eo.educationLevel " +
                "INNER JOIN FETCH ps.repartition " +
                "WHERE ps.id.promotionId = : id " +
                "ORDER by s.nume, s.prenume";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idPromotion);

        List<StudentEntity> listOfStudentEntities = query.getResultList();
        return listOfStudentEntities;

    }


    public void addStudentToPromotion(int idPromotion, StudentEntity studentEntity, Repartition repartition) {
        PromotionEntity promotionEntity = entityManager.find(PromotionEntity.class, idPromotion);

        PromotionStudentEntity promotionStudentEntity = new PromotionStudentEntity();

        promotionStudentEntity.setPromotionEntity(promotionEntity);
        promotionStudentEntity.setStudentEntity(studentEntity);
        promotionStudentEntity.setRepartition(repartition);

        studentEntity.addPromotionStudent(promotionStudentEntity);

        entityManager.persist(promotionStudentEntity);
    }

    public void deleteStudentFromPromotion(int idPromotion, int idStudent) {
        String jpql = "FROM PromotionStudentEntity ps " +
                "WHERE ps.promotionEntity.id = : idPromotion AND ps.studentEntity.id = : idStudent";

        TypedQuery<PromotionStudentEntity> query = entityManager.createQuery(jpql, PromotionStudentEntity.class);
        query.setParameter("idPromotion", idPromotion);
        query.setParameter("idStudent", idStudent);

        PromotionStudentEntity promotionStudentEntity = query.getSingleResult();

        entityManager.remove(promotionStudentEntity);
    }

/*
         Helper methods;  To be used in service for business logic
 */

    public long countNrStudentsBuget(int idPromotion) {
        String jpql = "SELECT count(ps.studentEntity) " +
                "FROM PromotionStudentEntity ps " +
                "WHERE ps.promotionEntity.id = : id AND ps.repartition.id = 1";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idPromotion);

        long nrStudentsBuget = (long) query.getSingleResult();

        return nrStudentsBuget;

    }

    public long countNrStudentsTaxa(int idPromotion) {
        String jpql = "SELECT count(ps.studentEntity) " +
                "FROM PromotionStudentEntity ps " +
                "WHERE ps.promotionEntity.id = : id AND ps.repartition.id = 2";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idPromotion);

        long nrStudentsBuget = (long) query.getSingleResult();

        return nrStudentsBuget;
    }


    public PromotionEntity getPromotionById(int idPromotion) {
        PromotionEntity promotionEntity = entityManager.find(PromotionEntity.class, idPromotion);
        return promotionEntity;
    }

}
