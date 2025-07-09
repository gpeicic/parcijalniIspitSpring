package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Polaznik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long polaznikId;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;

    public Polaznik(Long polaznikId, String ime, String prezime) {
        this.polaznikId = polaznikId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Polaznik() {

    }

    public Long getPolaznikId() {
        return polaznikId;
    }

    public void setPolaznikId(Long polaznikId) {
        this.polaznikId = polaznikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}