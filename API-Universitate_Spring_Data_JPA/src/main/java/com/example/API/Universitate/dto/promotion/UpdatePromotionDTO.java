package com.example.API.Universitate.dto.promotion;

import javax.validation.constraints.NotNull;

public class UpdatePromotionDTO {
    @NotNull(message = "Locurile la buget nu pot fi goale")
    public int nrLocuriBuget;

    @NotNull(message = "Locurile la taxa nu pot fi goale")
    public int nrLocuriTaxa;
}
