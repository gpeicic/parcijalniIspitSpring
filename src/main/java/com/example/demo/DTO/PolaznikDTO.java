package com.example.demo.DTO;


import jakarta.validation.constraints.NotBlank;

public class PolaznikDTO {
    public Long id;
    @NotBlank
    public String ime;
    @NotBlank
    public String prezime;
}
