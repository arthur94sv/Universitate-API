package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.professor.UpdateProfessorDTO;
import com.example.API.Universitate.dto.professor.CreateProfessorDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorDTO;
import com.example.API.Universitate.dto.professor.DisplayProfessorForCollegeDTO;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.mapper.ProfessorMapperImpl;
import com.example.API.Universitate.repository.CollegeRepository;
import com.example.API.Universitate.repository.DepartmentRepository;
import com.example.API.Universitate.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessorService {
    private CollegeRepository collegeRepository;
    private ProfessorRepository professorRepository;
    private DepartmentRepository departmentRepository;

    private ProfessorMapperImpl professorMapper;

    @Autowired
    public ProfessorService(CollegeRepository collegeRepository,
                            ProfessorRepository professorRepository,
                            DepartmentRepository departmentRepository,
                            ProfessorMapperImpl professorMapper) {
        this.collegeRepository = collegeRepository;
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
        this.professorMapper = professorMapper;
    }

    public List<DisplayProfessorForCollegeDTO> getAllProfessorForCollege(int idCollege) {
        if (collegeRepository.existsById(idCollege)) {
            List<ProfessorEntity> professors = professorRepository.getAllProfessorsByCollegeId(idCollege);
            return professors.stream()
                    .map(professorEntity -> professorMapper.toDisplayProfessorForCollegeDTO(professorEntity))
                    .collect(Collectors.toList());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu a fost gasita o facultate cu id-ul: " + idCollege);
    }

    public List<DisplayProfessorDTO> searchProfessor(String nume, String prenume) {
        if (prenume == null)
            return searchProfessorByNume(nume);
        else
            return searchProfessorByNumeAndPrenume(nume, prenume);
    }

            private List<DisplayProfessorDTO> searchProfessorByNume(String name) {
                List<ProfessorEntity> professors = professorRepository.findByNume(name)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista profesori cu numele " + name));
                return professors.stream()
                        .map(professorEntity -> professorMapper.toDisplayProfessorDTO(professorEntity))
                        .collect(Collectors.toList());
            }

            private List<DisplayProfessorDTO> searchProfessorByNumeAndPrenume(String name, String prenume) {
                List<ProfessorEntity> professors = professorRepository.findByNumeAndPrenume(name, prenume)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista profesori cu numele " + name + " si prenumele " + prenume));
                return professors.stream()
                        .map(professorEntity -> professorMapper.toDisplayProfessorDTO(professorEntity))
                        .collect(Collectors.toList());
            }

    public void updateProfessor(int idProfessor, UpdateProfessorDTO updateProfessorDTO) {
        if (professorRepository.existsById(idProfessor)) {
            ProfessorEntity professorToUpdate = professorRepository.getOne(idProfessor);

            professorToUpdate.setNume(updateProfessorDTO.nume);
            professorToUpdate.setPrenume(updateProfessorDTO.prenume);
            professorToUpdate.setEmail(updateProfessorDTO.email);
            professorToUpdate.setGrad(updateProfessorDTO.grad);

            professorRepository.save(professorToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista prfesor cu id-ul: " + idProfessor);
    }

    public void addProfessor(int idDepartment, CreateProfessorDTO createProfessorDTO) {
        if (departmentRepository.existsById(idDepartment)) {
            ProfessorEntity professorEntity = professorMapper.toEntity(createProfessorDTO);

            DepartmentEntity departmentEntity = departmentRepository.getOne(idDepartment);

            professorEntity.setDepartmentEntity(departmentEntity);

            professorRepository.save(professorEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamentul cu id-ul: " + idDepartment + " nu a fost gasit");
    }


    public void deleteProfessor(int idProfessor) {
        if (professorRepository.existsById(idProfessor))
            professorRepository.deleteById(idProfessor);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesorul cu id-ul: " + idProfessor + " nu a fost gasit");
    }


}
