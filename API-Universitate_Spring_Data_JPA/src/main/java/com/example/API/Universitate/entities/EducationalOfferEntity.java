package com.example.API.Universitate.entities;

import com.example.API.Universitate.entities.lookUpTable.EducationLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oferta_educationala")
public class EducationalOfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oferta-educ-gen")
    @SequenceGenerator(name = "oferta-educ-gen", sequenceName = "pk_oferta_educationala", allocationSize = 1)
    private Integer id;

    @Column(name = "nume")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultate")
    @JsonIgnore
    private CollegeEntity collegeEntity;

    @OneToMany(mappedBy = "educationalOfferEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PromotionEntity> listOfPromotionEntities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ciclu_studii")
    private EducationLevel educationLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollegeEntity getCollegeEntity() {
        return collegeEntity;
    }

    public void setCollegeEntity(CollegeEntity collegeEntity) {
        this.collegeEntity = collegeEntity;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<PromotionEntity> getListOfPromotionEntities() {
        return listOfPromotionEntities;
    }

    public void setListOfPromotionEntities(List<PromotionEntity> listOfPromotionEntities) {
        this.listOfPromotionEntities = listOfPromotionEntities;
    }
}
