package com.example.API.Universitate.dto;

import javax.validation.constraints.NotNull;

public class CollegeDTO {
    public Integer id;

    @NotNull(message = "Numele facultatii este obligatoriu de introdus")
    public String nume;
    public String strada;
    public Integer nr;
    public String telefon;
}
