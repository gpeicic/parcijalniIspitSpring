package com.example.demo.service;

import com.example.demo.DTO.PolaznikDTO;
import com.example.demo.repository.PolaznikRepository;
import com.example.demo.model.Polaznik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolaznikService {

    @Autowired
    private PolaznikRepository polaznikRepository;

    public List<Polaznik> getAll(){
        return polaznikRepository.findAll();
    }
    public Polaznik getById(Long id) {
        return polaznikRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Polaznik save(Polaznik polaznik) {
        Polaznik p = new Polaznik();
        p.setPolaznikId(polaznik.getPolaznikId());
        p.setIme(polaznik.getIme());
        p.setPrezime(polaznik.getPrezime());
        return polaznikRepository.save(p);
    }
    public void delete(Long id){
        polaznikRepository.deleteById(id);
    }
}
