package com.example.API.Universitate.dto.course;

import javax.validation.constraints.NotNull;

public class UpdateCourseDTO {
    @NotNull(message = "Numele cursului nu poate sa fie null")
    public String name;
}
