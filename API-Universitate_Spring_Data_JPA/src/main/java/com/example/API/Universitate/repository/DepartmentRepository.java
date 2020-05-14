package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    @Query("FROM DepartmentEntity dep " +
            "INNER JOIN FETCH dep.collegeEntity c " +
            "WHERE c.id = ?1 " +
            "ORDER BY dep.departmentName")
    Optional<List<DepartmentEntity>> findDepartmentsByCollegeId(int idCollege);
}

