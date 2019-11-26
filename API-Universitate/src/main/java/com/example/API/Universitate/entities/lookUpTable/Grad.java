package com.example.API.Universitate.entities.lookUpTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grad_profesor")
public class Grad {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "grad")
    private String grad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}
