package com.example.API.Universitate.entities;

import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student-gen")
    @SequenceGenerator(name = "student-gen", sequenceName = "pk_student", allocationSize = 1)
    private Integer id;
    private String nume;
    private String prenume;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentEntity",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<PromotionStudentEntity> promotionStudentEntities;

    public StudentEntity() {
        this.promotionStudentEntities = new ArrayList<>();
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

    public List<PromotionStudentEntity> getPromotionStudentEntities() {
        return promotionStudentEntities;
    }

    public void setPromotionStudentEntities(List<PromotionStudentEntity> promotionStudentEntities) {
        this.promotionStudentEntities = promotionStudentEntities;
    }

    public void setPromotion(PromotionStudentEntity promotionStudentEntity) {
        this.promotionStudentEntities.add(promotionStudentEntity);
    }
}
