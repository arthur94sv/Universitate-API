package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.professor.GetProfessor;
import com.example.API.Universitate.dto.promotion.GetPromotion;
import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.entities.lookUpTable.Grad;
import com.example.API.Universitate.mapper.CursMapperImpl;
import com.example.API.Universitate.mapper.ProfessorMapperImpl;
import com.example.API.Universitate.repository.ProfessorRepository;
import org.junit.Before;
import org.junit.Test;
import sun.java2d.windows.GDIRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfessorServiceTest {
    ProfessorRepository mockedProfessorRepository = mock(ProfessorRepository.class);
    List<ProfessorEntity> listReturnedByRepository = new ArrayList<>();

    ProfessorMapperImpl professorMapper = new ProfessorMapperImpl();
    CursMapperImpl cursMapper = new CursMapperImpl();

    List<GetProfessor> listExpected = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setId(23);
        professorEntity.setNume("Popescu");
        professorEntity.setPrenume("George");

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1);
        departmentEntity.setDepartmentName("Inteligenta artificiala");
        CollegeEntity collegeEntity = new CollegeEntity();
        collegeEntity.setId(1);
        collegeEntity.setNume("Facultatea de Informatica");
        departmentEntity.setCollegeEntity(collegeEntity);
        professorEntity.setDepartmentEntity(departmentEntity);

        Grad grad = new Grad();
        grad.setId(4);
        grad.setGrad("profesor universitar");

        professorEntity.setGrad(grad);


        listReturnedByRepository.add(professorEntity);


        GetProfessor professor = new GetProfessor();
        professor.id = 23;
        professor.nume = "Popescu";
        professor.prenume = "George";
        professor.grad = "profesor universitar";
        professor.department = "Inteligenta artificiala";
        professor.college = "Facultatea de Informatica";
        listExpected.add(professor);

    }

    @Test
    public void searchProfessorWhenPrenumeIsNull() {
        when(mockedProfessorRepository.searchProfessorByName("Popescu")).thenReturn(listReturnedByRepository);

        ProfessorService professorService = new ProfessorService(mockedProfessorRepository, professorMapper, cursMapper);

        List<GetProfessor> listReturnedByService = professorService.searchProfessor("Popescu", null);

        assertEquals(listReturnedByService, listExpected);

    }

    @Test
    public void searchProfessorWhenPrenumeIsNotNull() {
        when(mockedProfessorRepository.searchProfessorByNumeAndPrenume("Popescu", "George")).thenReturn(listReturnedByRepository);

        ProfessorService professorService = new ProfessorService(mockedProfessorRepository, professorMapper, cursMapper);

        List<GetProfessor> listReturnedByService = professorService.searchProfessor("Popescu", "George");

        assertEquals(listReturnedByService, listExpected);

    }


}
