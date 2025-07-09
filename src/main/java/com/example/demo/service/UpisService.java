package com.example.demo.service;

import com.example.demo.model.Upis;
import com.example.demo.repository.UpisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpisService {

    @Autowired
    private UpisRepository upisRepository;

    public List<Upis> getAll() {
        return upisRepository.findAll();
    }

    public Upis getById(Long id) {
        return upisRepository.findById(id).orElseThrow(() -> new RuntimeException("Upis not found"));
    }

    public Upis save(Upis upis) {
        return upisRepository.save(upis);
    }

    public void delete(Long id) {
        upisRepository.deleteById(id);
    }
}