package com.example.API.Universitate.dto.promotion;

import javax.validation.constraints.Min;


public class PromotionForEducOffer {
    @Min(value = 4, message = "Anul de inceput trebuie sa aiba 4 cifre")
    public int startYear;

    @Min(value = 4, message = "Anul de sfarsit trebuie sa aiba 4 cifre")
    public int endYear;

    @Min(value = 1, message = "Trebuie sa existe minim 1 loc disponibil la buget")
    public int nrLocuriBuget;

    @Min(value = 1, message = "Trebuie sa existe minim 1 loc disponibil la taxa")
    public int nrLocuriTaxa;
}
