package com.example.API.Universitate.dto.college;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class UpdateCollegeDTO {
    @NotBlank(message = "Numele facultatii nu are voie sa fie gol")
    public String nume;
    public String strada;
    @Positive
    public Integer nr;
    public String telefon;
}
