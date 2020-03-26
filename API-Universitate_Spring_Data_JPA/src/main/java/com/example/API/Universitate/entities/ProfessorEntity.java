package com.example.API.Universitate.entities;

import com.example.API.Universitate.entities.lookUpTable.Grad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesor")
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor-gen")
    @SequenceGenerator(name = "professor-gen", sequenceName = "pk_profesor", allocationSize = 1)
    private Integer id;
    private String nume;
    private String prenume;
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grad")
    private Grad grad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departament")
    private DepartmentEntity departmentEntity;

    @OneToMany(mappedBy = "professorEntity", fetch = FetchType.LAZY)
    private List<CourseEntity> listOfCurses;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }


    public List<CourseEntity> getListOfCurses() {
        return listOfCurses;
    }

    public void setListOfCurses(List<CourseEntity> listOfCurses) {
        this.listOfCurses = listOfCurses;
    }
}
