package com.example.demo.ControllerTest.ProgramObrazovanjaController;


import com.example.demo.DTO.ProgramObrazovanjaDTO;
import com.example.demo.controller.ProgramObrazovanjaController;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.service.ProgramObrazovanjaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProgramObrazovanjaControllerUnitTest {

    @InjectMocks
    private ProgramObrazovanjaController controller;

    @Mock
    private ProgramObrazovanjaService service;

    @Mock
    private EntityMapper mapper;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfDTOs() {
        ProgramObrazovanja entity = new ProgramObrazovanja();
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();

        when(service.getAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        ResponseEntity<List<ProgramObrazovanjaDTO>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(dto);
        verify(service).getAll();
        verify(mapper).toDTO(entity);
    }

    @Test
    void getById_ReturnsDTO_WhenExists() {
        Long id = 1L;
        ProgramObrazovanja entity = new ProgramObrazovanja();
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();

        when(service.getById(id)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);

        ResponseEntity<ProgramObrazovanjaDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void getById_ReturnsNotFound_WhenNull() {
        Long id = 1L;

        when(service.getById(id)).thenReturn(null);

        ResponseEntity<ProgramObrazovanjaDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void create_ReturnsBadRequest_IfValidationFails() {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();

        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<ProgramObrazovanjaDTO> response = controller.create(dto, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verifyNoInteractions(service);
    }

    @Test
    void create_ReturnsCreated_WhenValid() {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        ProgramObrazovanja entity = new ProgramObrazovanja();
        ProgramObrazovanja saved = new ProgramObrazovanja();
        ProgramObrazovanjaDTO savedDto = new ProgramObrazovanjaDTO();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(service.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(savedDto);

        ResponseEntity<ProgramObrazovanjaDTO> response = controller.create(dto, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(savedDto);
    }

    @Test
    void delete_ReturnsNoContent() {
        Long id = 1L;

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(service).delete(id);
    }
}
