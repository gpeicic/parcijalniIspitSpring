package com.example.demo.controller;

import com.example.demo.DTO.PolaznikDTO;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.Polaznik;
import com.example.demo.service.PolaznikService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polaznici")
public class PolaznikController {

    @Autowired
    private PolaznikService service;

    @Autowired
    private EntityMapper mapper;

    @GetMapping
    public ResponseEntity<List<PolaznikDTO>> getAll() {
        List<PolaznikDTO> polaznici = service.getAll().stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(polaznici);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolaznikDTO> getById( @PathVariable Long id) {
        PolaznikDTO dto = mapper.toDTO(service.getById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PolaznikDTO> create(@Valid @RequestBody PolaznikDTO dto,  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
           return ResponseEntity.badRequest().build();
        }
        Polaznik saved = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
