package com.example.API.Universitate.dto.student;

import com.example.API.Universitate.entities.lookUpTable.Repartition;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddStudentToPromotionDTO {
    @NotBlank(message = "Numele studentului este obligatoriu")
    public String nume;

    @NotBlank(message = "Prenumele studentului este obligatoriu")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;

    @NotNull(message = "Repartitia este obligatorie")
    public Repartition repartition;
}
