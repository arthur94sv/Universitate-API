package com.example.API.Universitate.repository;


import com.example.API.Universitate.entities.CollegeEntity;
import com.example.API.Universitate.entities.DepartmentEntity;
import com.example.API.Universitate.entities.ProfessorEntity;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CollegeRepository {
    @PersistenceContext
    EntityManager entityManager;


    public List<CollegeEntity> getAllColleges() {
        String jpql = "FROM CollegeEntity college " +
                "ORDER BY college.nume";

        List<CollegeEntity> listOfCollegeEntities = entityManager.createQuery(jpql).getResultList();

        return listOfCollegeEntities;
    }


    private CollegeEntity getCollegeById(int idCollege) {
        CollegeEntity collegeEntity = entityManager.find(CollegeEntity.class, idCollege);
        return collegeEntity;
    }


    public void addCollege(CollegeEntity collegeEntity) {
        entityManager.persist(collegeEntity);
    }

    public void updateCollege(int idCollege, CollegeEntity collegeEntity) {
        CollegeEntity oldCollegeEntity = entityManager.find(CollegeEntity.class, idCollege);

        oldCollegeEntity.setNume(collegeEntity.getNume());
        oldCollegeEntity.setStrada(collegeEntity.getStrada());
        oldCollegeEntity.setNr(collegeEntity.getNr());
        oldCollegeEntity.setTelefon(collegeEntity.getTelefon());

    }

    public void deleteCollege(int idCollege) {
        CollegeEntity collegeEntity = entityManager.find(CollegeEntity.class, idCollege);
        entityManager.remove(collegeEntity);
    }
/*
                      DEPARTMENTS
*/

    public List<DepartmentEntity> getCollegeDepartments(int idCollege) {
        String jpql = "FROM DepartmentEntity dep " +
                "INNER JOIN FETCH dep.collegeEntity c " +
                "WHERE c.id =: id " +
                "ORDER BY dep.departmentName";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idCollege);

        List<DepartmentEntity> listOfDepartmentEntities = query.getResultList();

        return listOfDepartmentEntities;
    }

    public void addDepartmentToCollege(int idCollege, DepartmentEntity departmentEntity) {
        CollegeEntity collegeEntity = this.getCollegeById(idCollege);
        departmentEntity.setCollegeEntity(collegeEntity);
        entityManager.persist(departmentEntity);
    }


    public void updateDepartment(int idDepartment, DepartmentEntity departmentEntity) {
        DepartmentEntity oldDepartmentEntity = entityManager.find(DepartmentEntity.class, idDepartment);

        oldDepartmentEntity.setDepartmentName(departmentEntity.getDepartmentName());

        entityManager.persist(oldDepartmentEntity);
    }

    public void deleteDepartment(int idDepartment) {
        DepartmentEntity departmentEntityToBeDeleted = entityManager.find(DepartmentEntity.class, idDepartment);
        entityManager.remove(departmentEntityToBeDeleted);
    }

/*
                       PROFESSORS
 */

    public List<ProfessorEntity> getAllProfessorForCollege(int idCollege) {
        String jpql = "FROM ProfessorEntity prf " +
                "INNER JOIN FETCH prf.grad " +
                "INNER JOIN FETCH prf.departmentEntity dep " +
                "WHERE dep.collegeEntity.id = :id " +
                "ORDER BY prf.nume, prf.prenume, dep.departmentName";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", idCollege);

        List<ProfessorEntity> listOfProfessorEntities = query.getResultList();
        return listOfProfessorEntities;
    }

    public void addProfessorToDepartment(int idDep, ProfessorEntity professorEntity) {
        DepartmentEntity departmentEntity = entityManager.find(DepartmentEntity.class, idDep);
        professorEntity.setDepartmentEntity(departmentEntity);
        entityManager.persist(professorEntity);
    }

    public void deleteProfessor(int idProfessor) {
        ProfessorEntity professorEntity = entityManager.find(ProfessorEntity.class, idProfessor);
        entityManager.remove(professorEntity);
    }


}
