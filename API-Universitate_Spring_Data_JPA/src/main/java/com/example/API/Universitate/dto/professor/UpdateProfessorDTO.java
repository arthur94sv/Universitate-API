package com.example.API.Universitate.dto.professor;

import com.example.API.Universitate.entities.lookUpTable.Grad;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateProfessorDTO {
    @NotNull(message = "Numele profesorului este obligatoriu")
    public String nume;

    @NotNull(message = "Prenumele profesorului este obligatoriu")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;

    @NotNull(message = "Gradul este obligatoriu")
    public Grad grad;
}
