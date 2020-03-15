package com.example.API.Universitate.dto.promotion;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CreatePromotionDTO {

    @NotNull(message = "Anul de inceput trebuie sa aiba 4 cifre")
    @Min(4)
    public int startYear;


    @NotNull(message = "Anul de sfarsit trebuie sa aiba 4 cifre")
    @Min(4)
    public int endYear;

    @NotNull
    @Min(value = 1, message = "Trebuie sa existe minim 1 loc disponibil la buget")
    public int nrLocuriBuget;

    @NotNull
    @Min(value = 1, message = "Trebuie sa existe minim 1 loc disponibil la taxa")
    public int nrLocuriTaxa;
}
