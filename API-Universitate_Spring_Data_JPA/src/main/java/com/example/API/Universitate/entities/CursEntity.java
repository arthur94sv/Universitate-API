package com.example.API.Universitate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "curs")
public class CursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curs-gen")
    @SequenceGenerator(name = "curs-gen", sequenceName = "pk_curs", allocationSize = 1)
    private Integer id;

    @Column(name = "denumire")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    @JsonIgnore
    private ProfessorEntity professorEntity;

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

    public ProfessorEntity getProfessorEntity() {
        return professorEntity;
    }

    public void setProfessorEntity(ProfessorEntity professorEntity) {
        this.professorEntity = professorEntity;
    }
}
