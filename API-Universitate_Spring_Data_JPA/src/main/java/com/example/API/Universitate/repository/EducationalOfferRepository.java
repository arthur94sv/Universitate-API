package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.EducationalOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationalOfferRepository extends JpaRepository<EducationalOfferEntity, Integer> {

    @Query("FROM EducationalOfferEntity eo " +
            "INNER JOIN FETCH eo.educationLevel " +
            "WHERE eo.collegeEntity.id = ?1 " +
            "ORDER BY eo.educationLevel.id, eo.name")
    List<EducationalOfferEntity> getEducationalOffersByCollegeId(int idCollege);
}
