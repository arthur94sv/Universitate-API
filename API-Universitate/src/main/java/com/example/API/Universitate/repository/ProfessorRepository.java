package com.example.API.Universitate.repository;

import com.example.API.Universitate.entities.CursEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import com.example.API.Universitate.mapper.ProfessorMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProfessorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProfessorEntity> searchProfessorByName(String nume) {
        String jpql = "FROM ProfessorEntity p " +
                "INNER JOIN FETCH p.grad " +
                "INNER JOIN FETCH p.departmentEntity d " +
                "INNER JOIN FETCH d.collegeEntity " +
                "WHERE p.nume LIKE : nume";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("nume", "%" + nume + "%");

        List<ProfessorEntity> listOfProfessors = query.getResultList();

        return listOfProfessors;
    }

    public List<ProfessorEntity> searchProfessorByNumeAndPrenume(String nume, String prenume) {
        String jpql = "FROM ProfessorEntity p " +
                "INNER JOIN FETCH p.grad " +
                "INNER JOIN FETCH p.departmentEntity d " +
                "INNER JOIN FETCH d.collegeEntity " +
                "WHERE p.nume LIKE : nume AND p.prenume LIKE : prenume";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("nume", "%" + nume + "%");
        query.setParameter("prenume", "%" + prenume + "%");

        List<ProfessorEntity> listOfProfessors = query.getResultList();

        return listOfProfessors;
    }

    public void updateProfessor(int idProfessor, ProfessorEntity professorEntity) {
        ProfessorEntity oldProfessor = entityManager.find(ProfessorEntity.class, idProfessor);

        oldProfessor.setNume(professorEntity.getNume());
        oldProfessor.setPrenume(professorEntity.getPrenume());
        oldProfessor.setEmail(professorEntity.getEmail());
        oldProfessor.setGrad(professorEntity.getGrad());

    }

/*
                      COURSES
 */

    public void addCourse(int idProfessor, CursEntity cursEntity) {
        ProfessorEntity professorEntity = entityManager.find(ProfessorEntity.class, idProfessor);

        cursEntity.setProfessorEntity(professorEntity);

        entityManager.persist(cursEntity);
    }

    public List<CursEntity> getCoursesForProfessor(int idProfessor) {
        String jpql = "FROM CursEntity c " +
                "INNER JOIN FETCH c.professorEntity p " +
                "WHERE p.id = : id";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idProfessor);

        List<CursEntity> listOfCourses = query.getResultList();
        return listOfCourses;
    }

    public void updateCourse(int idCourse, CursEntity cursEntity) {
        CursEntity oldCurs = entityManager.find(CursEntity.class, idCourse);

        oldCurs.setName(cursEntity.getName());
    }

    public void deleteCourse(int idCourse) {
        CursEntity oldCurs = entityManager.find(CursEntity.class, idCourse);

        entityManager.remove(oldCurs);
    }
}
