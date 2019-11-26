package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.Department;
import com.example.API.Universitate.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toDTO(DepartmentEntity departmentEntity);

    @Mapping(target = "collegeEntity", ignore = true)
    @Mapping(target = "listOfProfessorEntities", ignore = true)
    DepartmentEntity toEntity(Department department);
}
