package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.student.GetStudent;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    StudentRepository mockedStudentRepository = mock(StudentRepository.class);
    List<StudentEntity> listReturnedByRepository = new ArrayList<>();

    StudentMapperImpl studentMapper = new StudentMapperImpl();

    List<GetStudent> listExpected = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId(1);
        studentEntity.setNume("Grigorescu");
        studentEntity.setPrenume("Vlad");

        listReturnedByRepository.add(studentEntity);


        GetStudent student = new GetStudent();
        student.id = 1;
        student.nume = "Grigorescu";
        student.prenume = "Vlad";

        listExpected.add(student);
    }

    @Test
    public void searchStudentWhenPrenumeIsNull() {
        when(mockedStudentRepository.searchStudentByName("Grigorescu")).thenReturn(listReturnedByRepository);

        StudentService studentService = new StudentService(mockedStudentRepository, studentMapper);

        List<GetStudent> listReturnedByService = studentService.searchStudent("Grigorescu", null);

        assertEquals(listExpected, listReturnedByService);

    }

    @Test
    public void searchStudentWhenPrenumeIsNotNull() {
        when(mockedStudentRepository.searchStudentByNumeAndPrenume("Grigorescu", "Vlad")).thenReturn(listReturnedByRepository);

        StudentService studentService = new StudentService(mockedStudentRepository, studentMapper);

        List<GetStudent> listReturnedByService = studentService.searchStudent("Grigorescu", "Vlad");

        assertEquals(listExpected, listReturnedByService);

    }
}
