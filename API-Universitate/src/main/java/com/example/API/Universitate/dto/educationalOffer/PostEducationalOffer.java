package com.example.API.Universitate.dto.educationalOffer;

import com.example.API.Universitate.entities.lookUpTable.EducationLevel;

import javax.validation.constraints.NotNull;

public class PostEducationalOffer {
    @NotNull(message = "Numele este obligatoriu")
    public String name;

    @NotNull(message = "Nivelul de educatie este obligatoriu")
    public EducationLevel educationLevel;
}
