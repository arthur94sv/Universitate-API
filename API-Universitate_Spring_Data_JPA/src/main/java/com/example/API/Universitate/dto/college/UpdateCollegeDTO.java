package com.example.API.Universitate.dto.college;

import javax.validation.constraints.NotNull;

public class UpdateCollegeDTO {
    @NotNull(message = "Numele facultatii nu are voie sa fie null")
    public String nume;
    public String strada;
    public Integer nr;
    public String telefon;
}
