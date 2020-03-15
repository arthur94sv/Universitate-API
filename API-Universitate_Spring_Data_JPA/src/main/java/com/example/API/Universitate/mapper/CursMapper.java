package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.curs.DisplayCurseDTO;
import com.example.API.Universitate.dto.curs.CreateCurseDTO;
import com.example.API.Universitate.entities.CursEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursMapper {

    @Mapping(target = "professorEntity", ignore = true)
    CursEntity toEntity(CreateCurseDTO createCurseDTO);


    DisplayCurseDTO toDisplayCursDTO(CursEntity cursEntity);

}
