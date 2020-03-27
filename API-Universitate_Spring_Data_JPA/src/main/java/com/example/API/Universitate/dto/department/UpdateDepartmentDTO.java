package com.example.API.Universitate.dto.department;

import javax.validation.constraints.NotBlank;

public class UpdateDepartmentDTO {
    @NotBlank(message = "Numele departamentului nu are voie sa fie gol")
    public String departmentName;
}
