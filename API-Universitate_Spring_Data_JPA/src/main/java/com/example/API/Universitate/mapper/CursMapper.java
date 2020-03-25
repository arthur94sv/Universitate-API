package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.course.DisplayCourseDTO;
import com.example.API.Universitate.dto.course.CreateCourseDTO;
import com.example.API.Universitate.entities.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursMapper {

    @Mapping(target = "professorEntity", ignore = true)
    CourseEntity toEntity(CreateCourseDTO createCourseDTO);


    DisplayCourseDTO toDisplayCursDTO(CourseEntity courseEntity);

}
