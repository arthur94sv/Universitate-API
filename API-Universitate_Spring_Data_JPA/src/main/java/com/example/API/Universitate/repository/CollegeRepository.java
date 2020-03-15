package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CollegeRepository extends JpaRepository<CollegeEntity, Integer> {

}
