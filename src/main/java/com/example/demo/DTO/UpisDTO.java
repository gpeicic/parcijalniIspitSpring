package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpisDTO {

    public Long id;
    @NotNull
    public Long polaznikId;
    @NotNull
    public Long programObrazovanjaId;
}
