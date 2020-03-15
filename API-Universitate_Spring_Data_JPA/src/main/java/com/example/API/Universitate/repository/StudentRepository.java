package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query("FROM StudentEntity s " +
            "INNER JOIN FETCH s.promotionStudentEntities ps " +
            "INNER JOIN FETCH ps.promotionEntity p " +
            "INNER JOIN FETCH ps.repartition " +
            "INNER JOIN FETCH p.educationalOfferEntity e " +
            "INNER JOIN FETCH e.collegeEntity " +
            "INNER JOIN FETCH e.educationLevel " +
            "WHERE s.nume LIKE %?1% ")
    Optional<List<StudentEntity>> searchStudentByName(String name);

    @Query("FROM StudentEntity s " +
            "INNER JOIN FETCH s.promotionStudentEntities ps " +
            "INNER JOIN FETCH ps.promotionEntity p " +
            "INNER JOIN FETCH ps.repartition " +
            "INNER JOIN FETCH p.educationalOfferEntity e " +
            "INNER JOIN FETCH e.collegeEntity " +
            "INNER JOIN FETCH e.educationLevel " +
            "WHERE s.nume LIKE %?1% AND s.prenume LIKE %?2%")
    Optional<List<StudentEntity>> searchStudentByNumeAndPrenume(String nume, String prenume);

    @Query("FROM StudentEntity s " +
            "INNER JOIN FETCH s.promotionStudentEntities ps " +
            "INNER JOIN FETCH ps.promotionEntity p " +
            "INNER JOIN FETCH p.educationalOfferEntity eo " +
            "INNER JOIN FETCH eo.educationLevel " +
            "INNER JOIN FETCH ps.repartition " +
            "WHERE ps.id.promotionId = ?1 " +
            "ORDER by s.nume, s.prenume")
    Optional<List<StudentEntity>> getStudentsForPromotion(int idPromotion);

    @Modifying
    @Transactional
    @Query("DELETE FROM PromotionStudentEntity ps " +
            "WHERE ps.promotionEntity.id = ?1 AND ps.studentEntity.id = ?2")
    void deleteStudentFromPromotion(int idPromotion, int idStudent);


}
