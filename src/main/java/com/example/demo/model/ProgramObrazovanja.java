package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProgramObrazovanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programObrazovanjaId;
    @NotBlank
    private String naziv;
    @NotNull
    private Integer csvet;

    public ProgramObrazovanja() {
    }


    public ProgramObrazovanja(Long programObrazovanjaId, String naziv, Integer csvet) {
        this.programObrazovanjaId = programObrazovanjaId;
        this.naziv = naziv;
        this.csvet = csvet;
    }

    public Long getProgramObrazovanjaId() {
        return programObrazovanjaId;
    }

    public void setProgramObrazovanjaId(Long programObrazovanjaId) {
        this.programObrazovanjaId = programObrazovanjaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getCsvet() {
        return csvet;
    }

    public void setCsvet(Integer csvet) {
        this.csvet = csvet;
    }
}