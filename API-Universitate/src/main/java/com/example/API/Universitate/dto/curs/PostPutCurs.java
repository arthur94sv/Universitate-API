package com.example.API.Universitate.dto.curs;

import javax.validation.constraints.NotNull;

public class PostPutCurs {
    @NotNull(message = "Numele cursului este obligatoriu")
    public String name;
}
