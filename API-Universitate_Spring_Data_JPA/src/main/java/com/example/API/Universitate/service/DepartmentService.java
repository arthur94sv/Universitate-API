package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.department.CreateDepartmentDTO;
import com.example.API.Universitate.dto.department.DisplayDepartmentDTO;
import com.example.API.Universitate.dto.department.UpdateDepartmentDTO;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.mapper.DepartmentMapperImpl;
import com.example.API.Universitate.repository.CollegeRepository;
import com.example.API.Universitate.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private CollegeRepository collegeRepository;
    private DepartmentRepository departmentRepository;

    private DepartmentMapperImpl departmentMapper;

    @Autowired
    public DepartmentService(CollegeRepository collegeRepository, DepartmentRepository departmentRepository, DepartmentMapperImpl departmentMapper) {
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;

        this.departmentMapper = departmentMapper;
    }

    public List<DisplayDepartmentDTO> getDepartmentsForColleges(int idCollege) {
        if (collegeRepository.existsById(idCollege)) {
            List<DepartmentEntity> departments = departmentRepository.getAllDepartmentsForCollege(idCollege);
            return departments.stream()
                    .map(departmentEntity -> departmentMapper.toDisplayDepartmentDTO(departmentEntity))
                    .collect(Collectors.toList());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamentele nu pot fi afisate deoarece nu a fost gasita o facultate cu id-ul: " + idCollege);
    }

    public void addDepartmentToCollege(int idCollege, CreateDepartmentDTO createDepartmentDTO) {
        if (collegeRepository.existsById(idCollege)) {
            DepartmentEntity departmentEntity = departmentMapper.toEntityFromCreateDepartmentDTO(createDepartmentDTO);

            CollegeEntity collegeEntity = collegeRepository.getOne(idCollege);
            departmentEntity.setCollegeEntity(collegeEntity);

            departmentRepository.save(departmentEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamentul nu poate fi adaugat deoarece nu a fost gasita o facultate cu id-ul: " + idCollege);
    }


    public void updateDepartment(int idDepartment, UpdateDepartmentDTO updateDepartmentDTO) {
        if (departmentRepository.existsById(idDepartment)) {
            DepartmentEntity departmentToUpdate = departmentRepository.getOne(idDepartment);

            departmentToUpdate.setDepartmentName(updateDepartmentDTO.departmentName);

            departmentRepository.save(departmentToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamentul cu id-ul: " + idDepartment + " nu a putut fi actualizat deoarece nu a fost gasit");
    }


    public void deleteDepartment(int idDepartment) {
        if (departmentRepository.existsById(idDepartment))
            departmentRepository.deleteById(idDepartment);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamentul cu id-ul: " + idDepartment + " nu a putut fi sters deoarece nu a fost gasit");
    }
}
