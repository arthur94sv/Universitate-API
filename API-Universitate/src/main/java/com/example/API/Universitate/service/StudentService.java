package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.student.GetStudent;
import com.example.API.Universitate.dto.student.PutStudent;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    private StudentRepository studentRepository;
    private StudentMapperImpl studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapperImpl studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<GetStudent> searchStudent(String nume, String prenume) {
        if (prenume != null) {
            List<StudentEntity> listOfStudentEntities = studentRepository.searchStudentByNumeAndPrenume(nume, prenume);
            List<GetStudent> listOfStudents = listOfStudentEntities.stream()
                    .map(studentEntity -> studentMapper.toDTOforStudent(studentEntity))
                    .collect(Collectors.toList());

            return listOfStudents;
        } else {
            List<StudentEntity> listOfStudentEntities = studentRepository.searchStudentByName(nume);
            List<GetStudent> listOfStudents = listOfStudentEntities.stream()
                    .map(studentEntity -> studentMapper.toDTOforStudent(studentEntity))
                    .collect(Collectors.toList());

            return listOfStudents;
        }
    }

    public void deleteStudent(int idStudent){
        studentRepository.deleteStudent(idStudent);
    }

    public void updateStudent(int idStudent, PutStudent putStudent){
        StudentEntity studentEntity = studentMapper.toEntityFromPutStudent(putStudent);
        studentRepository.updateStudent(idStudent, studentEntity);
    }
}
