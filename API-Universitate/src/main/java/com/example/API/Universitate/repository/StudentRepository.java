package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.StudentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentEntity> searchStudentByName(String name) {
        String jpql = "FROM StudentEntity s " +
                "INNER JOIN FETCH s.promotionStudentEntities ps " +
                "INNER JOIN FETCH ps.promotionEntity p " +
                "INNER JOIN FETCH ps.repartition " +
                "INNER JOIN FETCH p.educationalOfferEntity e " +
                "INNER JOIN FETCH e.collegeEntity " +
                "INNER JOIN FETCH e.educationLevel " +
                "WHERE s.nume LIKE : nume ";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("nume", "%" + name + "%");

        List<StudentEntity> listOfStudents = query.getResultList();
        return listOfStudents;
    }

    public List<StudentEntity> searchStudentByNumeAndPrenume(String nume, String prenume){
        String jpql = "FROM StudentEntity s " +
                "INNER JOIN FETCH s.promotionStudentEntities ps " +
                "INNER JOIN FETCH ps.promotionEntity p " +
                "INNER JOIN FETCH ps.repartition " +
                "INNER JOIN FETCH p.educationalOfferEntity e " +
                "INNER JOIN FETCH e.collegeEntity " +
                "INNER JOIN FETCH e.educationLevel " +
                "WHERE s.nume LIKE : nume AND s.prenume LIKE :prenume";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("nume", "%" + nume + "%");
        query.setParameter("prenume", "%" + prenume + "%");

        List<StudentEntity> listOfStudents = query.getResultList();
        return listOfStudents;
    }

    public void deleteStudent(int idStudent){
        StudentEntity studentEntity = entityManager.find(StudentEntity.class, idStudent);
        entityManager.remove(studentEntity);
    }

    public void updateStudent(int idStudent, StudentEntity studentEntity){
        StudentEntity oldStudentEntity = entityManager.find(StudentEntity.class, idStudent);

        oldStudentEntity.setNume(studentEntity.getNume());
        oldStudentEntity.setPrenume(studentEntity.getPrenume());
        oldStudentEntity.setEmail(studentEntity.getEmail());
    }
}
