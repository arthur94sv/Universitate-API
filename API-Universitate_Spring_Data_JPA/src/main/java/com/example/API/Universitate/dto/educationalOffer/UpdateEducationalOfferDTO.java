package com.example.API.Universitate.dto.educationalOffer;

import javax.validation.constraints.NotNull;

public class UpdateEducationalOfferDTO {

    @NotNull(message = "Numele este obligatoriu")
    public String name;
}
