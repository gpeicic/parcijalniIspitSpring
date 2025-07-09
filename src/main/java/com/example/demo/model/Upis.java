package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Upis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upisId;

    @ManyToOne
    private Polaznik polaznik;

    @ManyToOne
    private ProgramObrazovanja programObrazovanja;

    public Upis() {
    }

    public Upis(Long upisId, Polaznik polaznik, ProgramObrazovanja programObrazovanja) {
        this.upisId = upisId;
        this.polaznik = polaznik;
        this.programObrazovanja = programObrazovanja;
    }

    public Long getUpisId() {
        return upisId;
    }

    public void setUpisId(Long upisId) {
        this.upisId = upisId;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public ProgramObrazovanja getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(ProgramObrazovanja programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }
}