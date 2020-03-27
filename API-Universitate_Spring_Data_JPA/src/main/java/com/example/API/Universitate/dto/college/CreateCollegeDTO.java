package com.example.API.Universitate.dto.college;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CreateCollegeDTO {
    @NotBlank(message = "Numele facultatii este obligatoriu de introdus")
    public String nume;
    public String strada;
    @Positive
    public Integer nr;
    public String telefon;
}
