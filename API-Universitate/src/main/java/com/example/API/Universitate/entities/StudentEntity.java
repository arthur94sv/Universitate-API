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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student-gen")
    @SequenceGenerator(name = "student-gen", sequenceName = "pk_student", allocationSize = 1)
    private Integer id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentEntity", cascade = CascadeType.REMOVE)
    private List<PromotionStudentEntity> promotionStudentEntities;

    public StudentEntity(){
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

    public void addPromotionStudent(PromotionStudentEntity promotionStudentEntity){
        this.promotionStudentEntities.add(promotionStudentEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nume, that.nume) &&
                Objects.equals(prenume, that.prenume) &&
                Objects.equals(email, that.email) &&
                Objects.equals(promotionStudentEntities, that.promotionStudentEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, email, promotionStudentEntities);
    }
}
