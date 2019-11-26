package com.example.API.Universitate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departament")
public class DepartmentEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departament-gen")
    @SequenceGenerator(name = "departament-gen", sequenceName = "pk_departament", allocationSize = 1)
    private Integer id;

    @Column(name = "nume_departament")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultate")
    private CollegeEntity collegeEntity;

    @OneToMany(mappedBy = "departmentEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProfessorEntity> listOfProfessorEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public CollegeEntity getCollegeEntity() {
        return collegeEntity;
    }

    public void setCollegeEntity(CollegeEntity collegeEntity) {
        this.collegeEntity = collegeEntity;
    }

    public List<ProfessorEntity> getListOfProfessorEntities() {
        return listOfProfessorEntities;
    }

    public void setListOfProfessorEntities(List<ProfessorEntity> listOfProfessorEntities) {
        this.listOfProfessorEntities = listOfProfessorEntities;
    }
}
