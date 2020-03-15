package com.example.API.Universitate.dto.student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UpdateStudentDTO {
    @NotNull(message = "Numele e obligatoriu")
    public String nume;

    @NotNull(message = "Prenumele e obligatoriu")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;
}
