package com.example.API.Universitate.dto.educationalOffer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateEducationalOfferDTO {

    @NotBlank(message = "Numele este obligatoriu")
    public String name;
}
