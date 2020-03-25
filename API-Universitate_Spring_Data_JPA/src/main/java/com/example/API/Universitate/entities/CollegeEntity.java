package com.example.API.Universitate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facultate")
public class CollegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facultate-gen")
    @SequenceGenerator(name = "facultate-gen", sequenceName = "pk_facultate", allocationSize = 1)
    private Integer id;
    private String nume;
    private String strada;
    private Integer nr;
    private String telefon;

    @OneToMany(mappedBy = "collegeEntity", fetch = FetchType.LAZY)
    private List<DepartmentEntity> listOfDepartmentEntities;


    public CollegeEntity() {
        this.listOfDepartmentEntities = new ArrayList<>();
    }

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

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<DepartmentEntity> getListOfDepartmentEntities() {
        return listOfDepartmentEntities;
    }

    public void setListOfDepartmentEntities(List<DepartmentEntity> listOfDepartmentEntities) {
        this.listOfDepartmentEntities = listOfDepartmentEntities;
    }
}
