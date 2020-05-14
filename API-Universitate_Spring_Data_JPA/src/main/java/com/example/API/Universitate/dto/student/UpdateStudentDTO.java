package com.example.API.Universitate.dto.student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateStudentDTO {
    @NotBlank(message = "Numele e obligatoriu")
    public String nume;

    @NotBlank(message = "Prenumele e obligatoriu")
    public String prenume;

    @Email(message = "Format email invalid")
    public String email;
}
