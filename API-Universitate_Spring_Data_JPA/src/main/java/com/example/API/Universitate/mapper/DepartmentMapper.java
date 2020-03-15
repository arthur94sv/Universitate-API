package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.department.DisplayDepartmentDTO;
import com.example.API.Universitate.dto.department.CreateDepartmentDTO;
import com.example.API.Universitate.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DisplayDepartmentDTO toDisplayDepartmentDTO(DepartmentEntity departmentEntity);

    @Mapping(target = "collegeEntity", ignore = true)
    @Mapping(target = "listOfProfessorEntities", ignore = true)
    DepartmentEntity toEntity(DisplayDepartmentDTO displayDepartmentDTO);

    @Mapping(target = "collegeEntity", ignore = true)
    @Mapping(target = "listOfProfessorEntities", ignore = true)
    DepartmentEntity toEntityFromCreateDepartmentDTO(CreateDepartmentDTO createDepartmentDTO);

}
