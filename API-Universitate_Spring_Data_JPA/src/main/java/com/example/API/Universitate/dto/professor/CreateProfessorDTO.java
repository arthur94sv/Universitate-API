package com.example.API.Universitate.dto.professor;

import com.example.API.Universitate.entities.lookUpTable.Grad;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateProfessorDTO {
    @NotBlank(message = "Numele profesorului este obligatoriu")
    public String nume;

    @NotBlank(message = "Prenumele profesorului este obligatoriu")
    public String prenume;

    @Email()
    public String email;

    @NotNull(message = "Gradul este obligatoriu")
    public Grad grad;
}
