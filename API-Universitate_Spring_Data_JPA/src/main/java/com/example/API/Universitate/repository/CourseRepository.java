package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Query("FROM CourseEntity c " +
            "INNER JOIN FETCH c.professorEntity p " +
            "WHERE p.id = ?1")
    Optional<List<CourseEntity>> findCoursesByProfessorId(int idProfessor);
}
