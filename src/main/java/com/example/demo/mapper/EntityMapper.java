package com.example.demo.mapper;

import com.example.demo.DTO.PolaznikDTO;
import com.example.demo.DTO.ProgramObrazovanjaDTO;
import com.example.demo.DTO.UpisDTO;
import com.example.demo.model.Polaznik;
import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.model.Upis;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public PolaznikDTO toDTO(Polaznik entity) {
        PolaznikDTO dto = new PolaznikDTO();
        dto.id = entity.getPolaznikId();
        dto.ime = entity.getIme();
        dto.prezime = entity.getPrezime();
        return dto;
    }

    public Polaznik toEntity(PolaznikDTO dto) {
        Polaznik p = new Polaznik();
        p.setPolaznikId(dto.id);
        p.setIme(dto.ime);
        p.setPrezime(dto.prezime);
        return p;
    }

    public ProgramObrazovanjaDTO toDTO(ProgramObrazovanja entity) {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        dto.id = entity.getProgramObrazovanjaId();
        dto.naziv = entity.getNaziv();
        dto.csvet = entity.getCsvet();
        return dto;
    }

    public ProgramObrazovanja toEntity(ProgramObrazovanjaDTO dto) {
        ProgramObrazovanja p = new ProgramObrazovanja();
     //   p.setProgramObrazovanjaId(dto.id);
        p.setNaziv(dto.naziv);
        p.setCsvet(dto.csvet);
        return p;
    }

    public UpisDTO toDTO(Upis u) {
        UpisDTO dto = new UpisDTO();
        dto.id = u.getUpisId();
        dto.polaznikId = u.getPolaznik().getPolaznikId();
        dto.programObrazovanjaId = u.getProgramObrazovanja().getProgramObrazovanjaId();
        return dto;
    }

    public Upis toEntity(UpisDTO dto, Polaznik polaznik, ProgramObrazovanja program) {
        Upis u = new Upis();
       // u.setUpisId(dto.id);
        u.setPolaznik(polaznik);
        u.setProgramObrazovanja(program);
        return u;
    }
}

