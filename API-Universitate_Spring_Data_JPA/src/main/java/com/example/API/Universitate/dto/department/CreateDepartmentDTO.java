package com.example.API.Universitate.dto.department;

import javax.validation.constraints.NotBlank;

public class CreateDepartmentDTO {
    @NotBlank(message = "Numele departamentului este obligatoriu")
    public String departmentName;
}

