package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer> {

    @Query("FROM ProfessorEntity prf " +
            "INNER JOIN FETCH prf.grad " +
            "INNER JOIN FETCH prf.departmentEntity dep " +
            "WHERE dep.collegeEntity.id = ?1 " +
            "ORDER BY prf.nume, prf.prenume, dep.departmentName")
    Optional<List<ProfessorEntity>> findProfessorsByCollegeId(int idCollege);

    @Query("FROM ProfessorEntity p " +
            "INNER JOIN FETCH p.grad " +
            "INNER JOIN FETCH p.departmentEntity d " +
            "INNER JOIN FETCH d.collegeEntity " +
            "WHERE p.nume LIKE %?1%")
    Optional<List<ProfessorEntity>> findByNume(String nume);

    @Query("FROM ProfessorEntity p " +
            "INNER JOIN FETCH p.grad " +
            "INNER JOIN FETCH p.departmentEntity d " +
            "INNER JOIN FETCH d.collegeEntity " +
            "WHERE p.nume LIKE %?1% AND p.prenume LIKE %?2%")
    Optional<List<ProfessorEntity>> findByNumeAndPrenume(String nume, String prenume);
}
