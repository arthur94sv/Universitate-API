package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.College;
import com.example.API.Universitate.dto.Department;
import com.example.API.Universitate.dto.professor.GetProfessorForCollege;
import com.example.API.Universitate.dto.professor.PostProfessorForCollege;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.mapper.CollegeMapperImpl;
import com.example.API.Universitate.mapper.DepartmentMapperImpl;
import com.example.API.Universitate.mapper.ProfessorMapperImpl;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction
        .annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service

@Transactional
public class CollegeService {
    private CollegeRepository collegeRepository;

    private CollegeMapperImpl collegeMapper;
    private DepartmentMapperImpl departmentMapper;
    private ProfessorMapperImpl professorMapper;

    @Autowired
    public CollegeService(CollegeRepository collegeRepository, CollegeMapperImpl collegeMapper, DepartmentMapperImpl departmentMapper, ProfessorMapperImpl professorMapper) {
        this.collegeRepository = collegeRepository;
        this.collegeMapper = collegeMapper;
        this.departmentMapper = departmentMapper;
        this.professorMapper = professorMapper;
    }


    public List<College> getAllColleges() {
        List<CollegeEntity> listOfCollegeEntities = collegeRepository.getAllColleges();
        List<College> listOfColleges = listOfCollegeEntities.stream()
                .map(collegeEntity -> collegeMapper.toDTO(collegeEntity))
                .collect(Collectors.toList());

        return listOfColleges;
    }


    public void addCollege(College college) {
        CollegeEntity collegeEntity = collegeMapper.toEntity(college);
        collegeRepository.addCollege(collegeEntity);
    }


    public void updateCollege(int idCollege, College college) {
        CollegeEntity collegeEntity = collegeMapper.toEntity(college);
        collegeRepository.updateCollege(idCollege, collegeEntity);
    }


    public void deleteCollege(int idCollege) {
        collegeRepository.deleteCollege(idCollege);
    }

    /*
                               DEPARTMENTS
    */

    public List<Department> getCollegeDepartments(int idCollege) {
        List<DepartmentEntity> listOfDepartmentEntities = collegeRepository.getCollegeDepartments(idCollege);
        List<Department> listOfDepartments = listOfDepartmentEntities.stream()
                .map(departmentEntity -> departmentMapper.toDTO(departmentEntity))
                .collect(Collectors.toList());

        return listOfDepartments;
    }


    public void addDepartmentToCollege(int idCollege, Department department) {
        DepartmentEntity departmentEntity = departmentMapper.toEntity(department);
        collegeRepository.addDepartmentToCollege(idCollege, departmentEntity);
    }


    public void updateDepartment(int idDepartment, Department department) {
        DepartmentEntity departmentEntity = departmentMapper.toEntity(department);
        collegeRepository.updateDepartment(idDepartment, departmentEntity);
    }


    public void deleteDepartment(int idDepartment) {
        collegeRepository.deleteDepartment(idDepartment);
    }

/*
                          PROFESSORS
 */


    public List<GetProfessorForCollege> getAllProfessorForCollege(int idCollege) {
        List<ProfessorEntity> listOfProfessorEntities = collegeRepository.getAllProfessorForCollege(idCollege);
        List<GetProfessorForCollege> listOfProfessors = listOfProfessorEntities.stream()
                .map(professorEntity -> professorMapper.toDTOforCollege(professorEntity))
                .collect(Collectors.toList());

        return listOfProfessors;
    }


    public void addProfessorToDepartment(int idDep, PostProfessorForCollege postProfessorForCollege) {
        ProfessorEntity professorEntity = professorMapper.toEntity(postProfessorForCollege);
        collegeRepository.addProfessorToDepartment(idDep, professorEntity);
    }


    public void deleteProfessor(int idProfessor) {
        collegeRepository.deleteProfessor(idProfessor);
    }
}

