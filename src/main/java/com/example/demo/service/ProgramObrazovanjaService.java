package com.example.demo.service;

import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.repository.ProgramObrazovanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramObrazovanjaService {

    @Autowired
    private ProgramObrazovanjaRepository programObrazovanjaRepository;

    public List<ProgramObrazovanja> getAll() {
        return programObrazovanjaRepository.findAll();
    }

    public ProgramObrazovanja getById(Long id) {
        return programObrazovanjaRepository.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
    }

    public ProgramObrazovanja save(ProgramObrazovanja programObrazovanja) {
        ProgramObrazovanja po = new ProgramObrazovanja();
        po.setProgramObrazovanjaId(programObrazovanja.getProgramObrazovanjaId());
        po.setNaziv(programObrazovanja.getNaziv());
        po.setCsvet(programObrazovanja.getCsvet());
        return programObrazovanjaRepository.save(po);
    }


    public void delete(Long id) {
        programObrazovanjaRepository.deleteById(id);
    }
}
