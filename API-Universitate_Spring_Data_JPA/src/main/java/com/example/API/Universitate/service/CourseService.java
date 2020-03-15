package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.curs.CreateCurseDTO;
import com.example.API.Universitate.dto.curs.DisplayCurseDTO;
import com.example.API.Universitate.dto.curs.UpdateCourseDTO;
import com.example.API.Universitate.entities.CursEntity;
import com.example.API.Universitate.mapper.CursMapperImpl;
import com.example.API.Universitate.repository.CourseRepository;
import com.example.API.Universitate.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;
    private CursMapperImpl cursMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository, CursMapperImpl cursMapper) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.cursMapper = cursMapper;
    }

    public List<DisplayCurseDTO> getAllCoursesForProfessor(int idProfessor) {
        if (professorRepository.existsById(idProfessor)) {
            List<CursEntity> courses = courseRepository.getAllCoursesByProfessorId(idProfessor);
            return courses.stream()
                    .map(cursEntity -> cursMapper.toDisplayCursDTO(cursEntity))
                    .collect(Collectors.toList());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu a fost gasit un profesor cu id-ul: " + idProfessor);
    }

    public void addCourse(int idProfessor, CreateCurseDTO createCurseDTO) {
        if (professorRepository.existsById(idProfessor)) {
            CursEntity cursEntity = cursMapper.toEntity(createCurseDTO);
            courseRepository.save(cursEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista profesor cu id-ul: " + idProfessor);
    }

    public void updateCourse(int idCourse, UpdateCourseDTO updateCourseDTO) {
        if (courseRepository.existsById(idCourse)) {
            CursEntity cursToUpdate = courseRepository.getOne(idCourse);

            cursToUpdate.setName(updateCourseDTO.name);

            courseRepository.save(cursToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista curs cu id-ul: " + idCourse);
    }

    public void deleteCourse(int idCourse) {
        if (courseRepository.existsById(idCourse))
            courseRepository.deleteById(idCourse);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista curs cu id-ul: " + idCourse);
    }

}
