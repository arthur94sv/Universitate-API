package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.CursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CursEntity, Integer> {

    @Query("FROM CursEntity c " +
            "INNER JOIN FETCH c.professorEntity p " +
            "WHERE p.id = ?1")
    List<CursEntity> getAllCoursesByProfessorId(int idProfessor);
}
