package com.example.API.Universitate.dto.course;

import javax.validation.constraints.NotBlank;

public class CreateCourseDTO {
    @NotBlank(message = "Numele cursului este obligatoriu")
    public String name;
}
