package com.example.API.Universitate.entities.joinTable;

import com.example.API.Universitate.entities.PromotionEntity;
import com.example.API.Universitate.entities.StudentEntity;
import com.example.API.Universitate.entities.lookUpTable.Repartition;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;


@Entity
@Table(name = "promotie_student")
public class PromotionStudentEntity {

    @EmbeddedId
    private PromotionStudentEntityId id = new PromotionStudentEntityId();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_promotie")
    @MapsId("promotionId")
    private PromotionEntity promotionEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_student")
    @MapsId("studentId")
    private StudentEntity studentEntity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_repartitie")
    private Repartition repartition;

    public PromotionStudentEntityId getId() {
        return id;
    }

    public void setId(PromotionStudentEntityId id) {
        this.id = id;
    }

    public PromotionEntity getPromotionEntity() {
        return promotionEntity;
    }

    public void setPromotionEntity(PromotionEntity promotionEntity) {
        this.promotionEntity = promotionEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public Repartition getRepartition() {
        return repartition;
    }

    public void setRepartition(Repartition repartition) {
        this.repartition = repartition;
    }
}
