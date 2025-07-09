package com.example.demo.controller;

import com.example.demo.DTO.UpisDTO;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.Polaznik;
import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.model.Upis;
import com.example.demo.service.PolaznikService;
import com.example.demo.service.ProgramObrazovanjaService;
import com.example.demo.service.UpisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upisi")
public class UpisController {

    @Autowired
    private UpisService upisService;

    @Autowired
    private PolaznikService polaznikService;

    @Autowired
    private ProgramObrazovanjaService programObrazovanjaService;

    @Autowired
    private EntityMapper mapper;

    @GetMapping
    public ResponseEntity<List<UpisDTO>> getAll() {
        List<UpisDTO> list = upisService.getAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpisDTO> getById(@PathVariable Long id) {
        Upis u = upisService.getById(id);
        return ResponseEntity.ok(mapper.toDTO(u));
    }

    @PostMapping
    public ResponseEntity<UpisDTO> create(@Valid @RequestBody UpisDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Polaznik polaznik = polaznikService.getById(dto.polaznikId);
        ProgramObrazovanja program = programObrazovanjaService.getById(dto.programObrazovanjaId);

        Upis u = mapper.toEntity(dto, polaznik, program);
        Upis saved = upisService.save(u);

        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        upisService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
