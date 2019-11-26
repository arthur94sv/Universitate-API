package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.PutProfessor;
import com.example.API.Universitate.dto.professor.GetProfessor;
import com.example.API.Universitate.dto.professor.GetProfessorForCollege;
import com.example.API.Universitate.dto.professor.PostProfessorForCollege;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.entities.lookUpTable.Grad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    @Mapping(target = "department", source = "departmentEntity")
    GetProfessorForCollege toDTOforCollege(ProfessorEntity professorEntity);

    default String toStringGrad(Grad grad) {
        return grad.getGrad();
    }


    default String toStringDepartment(DepartmentEntity departmentEntity) {
        return departmentEntity.getDepartmentName();
    }

    @Mapping(target = "departmentEntity", ignore = true)
    @Mapping(target = "listOfCurses", ignore = true)
    ProfessorEntity toEntity(PostProfessorForCollege postProfessorForCollege);

    @Mapping(target = "department", source = "departmentEntity")
    @Mapping(target = "college", source = "departmentEntity.collegeEntity")
    GetProfessor toDTO(ProfessorEntity professorEntity);

    default String toStringCollege(CollegeEntity collegeEntity){
        return collegeEntity.getNume();
    }

    @Mapping(target = "departmentEntity", ignore = true)
    @Mapping(target = "listOfCurses", ignore = true)
    ProfessorEntity toEntityFromPutProfessor(PutProfessor putProfessor);
}
