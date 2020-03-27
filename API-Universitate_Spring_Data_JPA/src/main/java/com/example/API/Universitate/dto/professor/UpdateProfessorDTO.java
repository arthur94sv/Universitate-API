package com.example.API.Universitate.dto.professor;

import com.example.API.Universitate.entities.lookUpTable.Grad;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateProfessorDTO {
    @NotBlank(message = "Numele profesorului nu are voie sa fie gol")
    public String nume;

    @NotBlank(message = "Prenumele profesorului nu are voie sa fie gol")
    public String prenume;

    @Email()
    public String email;

    @NotNull(message = "Gradul nu are voie sa fie null")
    public Grad grad;
}
