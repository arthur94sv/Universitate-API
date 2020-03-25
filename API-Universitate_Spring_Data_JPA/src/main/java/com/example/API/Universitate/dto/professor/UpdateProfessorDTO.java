package com.example.API.Universitate.dto.professor;

import com.example.API.Universitate.entities.lookUpTable.Grad;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateProfessorDTO {
    @NotNull(message = "Numele profesorului nu are voie sa fie null")
    public String nume;

    @NotNull(message = "Prenumele profesorului nu are voie sa fie null")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;

    @NotNull(message = "Gradul nu are voie sa fie null")
    public Grad grad;
}
