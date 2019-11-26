package com.example.API.Universitate.dto.student;

import com.example.API.Universitate.entities.lookUpTable.Repartition;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AddStudentToPromotion {
    public Integer id;

    @NotNull(message = "Numele studentului este obligatoriu")
    public String nume;

    @NotNull(message = "Prenumele studentului este obligatoriu")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;

    @NotNull(message = "Repartitia este obligatorie")
    public Repartition repartition;
}
