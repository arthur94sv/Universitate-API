package com.example.API.Universitate.entities;

import com.example.API.Universitate.entities.joinTable.PromotionStudentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promotie")
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion-gen")
    @SequenceGenerator(name = "promotion-gen", sequenceName = "pk_promotie", allocationSize = 1)
    private Integer id;

    @JoinColumn(name = "id_oferta_educationala")
    @ManyToOne(fetch = FetchType.EAGER)
    private EducationalOfferEntity educationalOfferEntity;

    @Column(name = "an_inceput")
    private int startYear;

    @Column(name = "an_sfarsit")
    private int endYear;

    @Column(name = "nr_locuri_buget")
    private int nrLocuriBuget;

    @Column(name = "nr_locuri_taxa")
    private int nrLocuriTaxa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotionEntity")
    @JsonIgnore
    private List<PromotionStudentEntity> promotionStudentEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EducationalOfferEntity getEducationalOfferEntity() {
        return educationalOfferEntity;
    }

    public void setEducationalOfferEntity(EducationalOfferEntity educationalOfferEntity) {
        this.educationalOfferEntity = educationalOfferEntity;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getNrLocuriBuget() {
        return nrLocuriBuget;
    }

    public void setNrLocuriBuget(int nrLocuriBuget) {
        this.nrLocuriBuget = nrLocuriBuget;
    }

    public int getNrLocuriTaxa() {
        return nrLocuriTaxa;
    }

    public void setNrLocuriTaxa(int nrLocuriTaxa) {
        this.nrLocuriTaxa = nrLocuriTaxa;
    }

    public List<PromotionStudentEntity> getPromotionStudentEntity() {
        return promotionStudentEntity;
    }

    public void setPromotionStudentEntity(List<PromotionStudentEntity> promotionStudentEntity) {
        this.promotionStudentEntity = promotionStudentEntity;
    }
}
