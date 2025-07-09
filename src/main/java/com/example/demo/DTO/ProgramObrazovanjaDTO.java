package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProgramObrazovanjaDTO {

    public Long id;
    @NotBlank
    public String naziv;
    @NotNull
    public Integer csvet;
}