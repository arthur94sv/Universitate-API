package com.example.API.Universitate.dto.department;

import javax.validation.constraints.NotNull;

public class UpdateDepartmentDTO {
    @NotNull(message = "Numele departamentului nu are voie sa fie null")
    public String departmentName;
}
