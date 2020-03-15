package com.example.API.Universitate.dto.department;

import javax.validation.constraints.NotNull;

public class CreateDepartmentDTO {
    @NotNull(message = "Numele departamentului este obligatoriu")
    public String departmentName;
}
