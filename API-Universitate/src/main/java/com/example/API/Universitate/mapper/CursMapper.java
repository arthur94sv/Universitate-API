package com.example.API.Universitate.mapper;

import com.example.API.Universitate.dto.curs.Curs;
import com.example.API.Universitate.dto.curs.PostPutCurs;
import com.example.API.Universitate.entities.CursEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursMapper {

    @Mapping(target = "professorEntity", ignore = true)
    CursEntity toEntity(PostPutCurs postPutCurs);


    Curs toDTO(CursEntity cursEntity);

}
