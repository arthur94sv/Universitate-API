package com.example.API.Universitate.dto.college;

import javax.validation.constraints.NotNull;

public class CreateCollegeDTO {
    @NotNull(message = "Numele facultatii este obligatoriu de introdus")
    public String nume;
    public String strada;
    public Integer nr;
    public String telefon;
}
