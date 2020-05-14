package com.example.API.Universitate.service;

import com.example.API.Universitate.dto.student.AddStudentToPromotionDTO;
import com.example.API.Universitate.dto.student.DisplayStudentDTO;
import com.example.API.Universitate.dto.student.DisplayStudentForPromotionDTO;
import com.example.API.Universitate.dto.student.UpdateStudentDTO;
import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import com.example.API.Universitate.entities.lookUpTable.Repartition;
import com.example.API.Universitate.mapper.StudentMapperImpl;
import com.example.API.Universitate.repository.PromotionRepository;
import com.example.API.Universitate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    private StudentRepository studentRepository;
    private PromotionRepository promotionRepository;
    private StudentMapperImpl studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          PromotionRepository promotionRepository,
                          StudentMapperImpl studentMapper) {
        this.studentRepository = studentRepository;
        this.promotionRepository = promotionRepository;
        this.studentMapper = studentMapper;
    }

    public List<DisplayStudentDTO> searchStudent(String nume, String prenume) {
        if (prenume == null)
            return searchStudentByName(nume);
        else
            return searchStudentByNumeAndPrenume(nume, prenume);
    }

            private List<DisplayStudentDTO> searchStudentByNumeAndPrenume(String nume, String prenume) {
                List<StudentEntity> students = studentRepository.searchStudentByNumeAndPrenume(nume, prenume)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista studenti cu numele: " + nume + " si prenumele: " + prenume));
                return students.stream()
                        .map(studentEntity -> studentMapper.toDisplayStudentDTO(studentEntity))
                        .collect(Collectors.toList());
            }

            private List<DisplayStudentDTO> searchStudentByName(String nume) {
                List<StudentEntity> students = studentRepository.searchStudentByName(nume)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista studenti cu numele: " + nume));
                return students.stream()
                        .map(studentEntity -> studentMapper.toDisplayStudentDTO(studentEntity))
                        .collect(Collectors.toList());
            }

    public List<DisplayStudentForPromotionDTO> getStudentsForPromotion(int idPromotion) {
        List<StudentEntity> listOfStudentEntities = studentRepository.findStudentsByPromotionId(idPromotion)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista promite cu id-ul: " + idPromotion));
        return listOfStudentEntities.stream()
                .map(studentEntity -> studentMapper.toDisplayStudentForPromotionDTO(studentEntity))
                .collect(Collectors.toList());
    }

    public void updateStudent(int idStudent, UpdateStudentDTO updateStudentDTO) {
        if (studentRepository.existsById(idStudent)) {
            StudentEntity studentToUpdate = studentRepository.getOne(idStudent);

            studentToUpdate.setNume(updateStudentDTO.nume);
            studentToUpdate.setPrenume(updateStudentDTO.prenume);
            studentToUpdate.setEmail(updateStudentDTO.email);

            studentRepository.save(studentToUpdate);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista student cu id-ul: " + idStudent);
    }

    public void deleteStudent(int idStudent) {
        if (studentRepository.existsById(idStudent))
            studentRepository.deleteById(idStudent);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista student cu id-ul: " + idStudent);
    }

    public void deleteStudentFromPromotion(int idPromotion, int idStudent) {
        if (studentRepository.existsById(idStudent) && promotionRepository.existsById(idPromotion))
            studentRepository.deleteStudentFromPromotion(idPromotion, idStudent);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista promotia cu id-ul: " + idPromotion + " si nici studentul cu id-ul: " + idStudent);

    }

    public void addStudentToPromotion(int idPromotion, AddStudentToPromotionDTO addStudentToPromotionDTO) {
        if (promotionRepository.existsById(idPromotion)) {
            StudentEntity studentEntity = studentMapper.toEntityFromAddStudentToPromotionDTO(addStudentToPromotionDTO);
            PromotionStudentEntity promotionStudentEntity = createPromotionStudentEntity(idPromotion, studentEntity, addStudentToPromotionDTO);

            studentEntity.setPromotion(promotionStudentEntity);

            addStudentToPromotionBasedOnRepartition(idPromotion, promotionStudentEntity.getPromotionEntity(), promotionStudentEntity.getPromotionEntity().getId(), studentEntity);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nu exista promotie cu id-ul: " + idPromotion);
    }

            private PromotionStudentEntity createPromotionStudentEntity(int idPromotion, StudentEntity studentEntity, AddStudentToPromotionDTO addStudentToPromotionDTO) {
                PromotionEntity promotionEntity = promotionRepository.getOne(idPromotion);
                Repartition repartition = addStudentToPromotionDTO.repartition;

                PromotionStudentEntity promotionStudentEntity = new PromotionStudentEntity();
                promotionStudentEntity.setStudentEntity(studentEntity);
                promotionStudentEntity.setPromotionEntity(promotionEntity);
                promotionStudentEntity.setRepartition(repartition);

                return promotionStudentEntity;
            }

            private void addStudentToPromotionBasedOnRepartition(int idRepartition, PromotionEntity promotionEntity, int idPromotion, StudentEntity studentEntity) {
                switch (idRepartition) {
                    // buget
                    case 1:
                        addStudentToPromotionBuget(promotionEntity, idPromotion, studentEntity);
                        break;
                    // taxa
                    case 2:
                        addStudentToPromotionTaxa(promotionEntity, idPromotion, studentEntity);
                        break;
                }
            }

                    private void addStudentToPromotionBuget(PromotionEntity promotionEntity, int idPromotion, StudentEntity studentEntity) {
                        if ((long) promotionEntity.getNrLocuriBuget() > promotionRepository.countNrStudentsBuget(idPromotion))
                            studentRepository.save(studentEntity);
                        else
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nu mai exista locuri disponibile la buget");
                    }

                    private void addStudentToPromotionTaxa(PromotionEntity promotionEntity, int idPromotion, StudentEntity studentEntity) {
                        if ((long) promotionEntity.getNrLocuriTaxa() > promotionRepository.countNrStudentsTaxa(idPromotion))
                            studentRepository.save(studentEntity);
                        else
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nu mai exista locuri disponibile la taxa");
                    }
}
