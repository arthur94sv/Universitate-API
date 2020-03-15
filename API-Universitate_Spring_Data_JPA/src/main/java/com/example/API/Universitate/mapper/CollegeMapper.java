package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.college.CreateCollegeDTO;
import com.example.API.Universitate.dto.college.DisplayCollegeDTO;
import com.example.API.Universitate.entities.CollegeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollegeMapper {
    DisplayCollegeDTO toDisplayCollegeDTO(CollegeEntity collegeEntity);

    @Mapping(target = "listOfDepartmentEntities", ignore = true)
    CollegeEntity toEntityFromCreateCollegeDTO(CreateCollegeDTO createCollegeDTO);

}
