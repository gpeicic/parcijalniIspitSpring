package com.example.demo.controller;

import com.example.demo.DTO.ProgramObrazovanjaDTO;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.service.ProgramObrazovanjaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obrazovanja")
public class ProgramObrazovanjaController {

    @Autowired
    private ProgramObrazovanjaService service;

    @Autowired
    private EntityMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProgramObrazovanjaDTO>> getAll() {
        List<ProgramObrazovanjaDTO> list = service.getAll().stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramObrazovanjaDTO> getById(@PathVariable Long id) {
        ProgramObrazovanja program = service.getById(id);
        if (program == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDTO(program));
    }

    @PostMapping
    public ResponseEntity<ProgramObrazovanjaDTO> create(@Valid @RequestBody ProgramObrazovanjaDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        ProgramObrazovanja saved = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
