package com.example.API.Universitate.dto.course;

import javax.validation.constraints.NotBlank;

public class UpdateCourseDTO {
    @NotBlank(message = "Numele cursului nu poate sa fie gol")
    public String name;
}
