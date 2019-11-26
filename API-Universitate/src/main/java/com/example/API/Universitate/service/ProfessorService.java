package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.PutProfessor;
import com.example.API.Universitate.dto.curs.Curs;
import com.example.API.Universitate.dto.curs.PostPutCurs;
import com.example.API.Universitate.dto.professor.GetProfessor;
import com.example.API.Universitate.entities.CursEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.mapper.CursMapperImpl;
import com.example.API.Universitate.mapper.ProfessorMapperImpl;
import com.example.API.Universitate.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessorService {
    private ProfessorRepository professorRepository;
    private ProfessorMapperImpl professorMapper;
    private CursMapperImpl cursMapper;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapperImpl professorMapper, CursMapperImpl cursMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.cursMapper = cursMapper;
    }

    public List<GetProfessor> searchProfessor(String nume, String prenume) {
        if (prenume != null) {
            List<ProfessorEntity> listOfProfessorEntities = professorRepository.searchProfessorByNumeAndPrenume(nume, prenume);
            List<GetProfessor> listOfProfessors = listOfProfessorEntities.stream()
                    .map(professorEntity -> professorMapper.toDTO(professorEntity))
                    .collect(Collectors.toList());

            return listOfProfessors;
        } else {
            List<ProfessorEntity> listOfProfessorEntities = professorRepository.searchProfessorByName(nume);
            List<GetProfessor> listOfProfessors = listOfProfessorEntities.stream()
                    .map(professorEntity -> professorMapper.toDTO(professorEntity))
                    .collect(Collectors.toList());

            return listOfProfessors;
        }
    }

    public void updateProfessor(int idProfessor, PutProfessor putProfessor) {
        ProfessorEntity professorEntity = professorMapper.toEntityFromPutProfessor(putProfessor);
        professorRepository.updateProfessor(idProfessor, professorEntity);
    }

/*
                            COURSES
 */

    public void addCourse(int idProfessor, PostPutCurs postPutCurs) {
        CursEntity cursEntity = cursMapper.toEntity(postPutCurs);
        professorRepository.addCourse(idProfessor, cursEntity);
    }

    public List<Curs> getCoursesForProfessor(int idProfessor) {
        List<CursEntity> listOfCourseEntities = professorRepository.getCoursesForProfessor(idProfessor);
        List<Curs> listOfCourses = listOfCourseEntities.stream()
                .map(cursEntity -> cursMapper.toDTO(cursEntity))
                .collect(Collectors.toList());

        return listOfCourses;
    }

    public void updateCourse(int idCourse, PostPutCurs postPutCurs) {
        CursEntity cursEntity = cursMapper.toEntity(postPutCurs);
        professorRepository.updateCourse(idCourse, cursEntity);
    }

    public void deleteCourse(int idCourse) {
        professorRepository.deleteCourse(idCourse);
    }
}
