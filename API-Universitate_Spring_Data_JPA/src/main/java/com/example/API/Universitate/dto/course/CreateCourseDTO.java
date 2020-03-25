package com.example.API.Universitate.dto.course;

import javax.validation.constraints.NotNull;

public class CreateCourseDTO {
    @NotNull(message = "Numele cursului este obligatoriu")
    public String name;
}
