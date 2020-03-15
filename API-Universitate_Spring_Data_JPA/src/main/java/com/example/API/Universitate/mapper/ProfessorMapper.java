package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.professor.CreateProfessorDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorForCollegeDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorDTO;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.entities.lookUpTable.Grad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    @Mapping(target = "department", source = "departmentEntity")
    DisplayProfessorForCollegeDTO toDisplayProfessorForCollegeDTO(ProfessorEntity professorEntity);

    @Mapping(target = "departmentEntity", ignore = true)
    @Mapping(target = "listOfCurses", ignore = true)
    ProfessorEntity toEntity(CreateProfessorDTO createProfessorDTO);

    @Mapping(target = "department", source = "departmentEntity")
    @Mapping(target = "college", source = "departmentEntity.collegeEntity")
    DisplayProfessorDTO toDisplayProfessorDTO(ProfessorEntity professorEntity);

    default String toStringCollege(CollegeEntity collegeEntity) {
        return collegeEntity.getNume();
    }

    default String toStringGrad(Grad grad) {
        return grad.getGrad();
    }

    default String toStringDepartment(DepartmentEntity departmentEntity) {
        return departmentEntity.getDepartmentName();
    }

}
