package com.example.API.Universitate.dto;

import javax.validation.constraints.NotNull;

public class Department {
    public Integer id;

    @NotNull(message = "Numele departamentului este obligatoriu")
    public String departmentName;
}
