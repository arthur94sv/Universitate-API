package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.College;
import com.example.API.Universitate.entities.CollegeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollegeMapper {
    College toDTO(CollegeEntity collegeEntity);

    @Mapping(target = "listOfDepartmentEntities", ignore = true)
    CollegeEntity toEntity(College college);

}
