package com.example.demo.ControllerTest.PolaznikController;


import com.example.demo.DTO.PolaznikDTO;
import com.example.demo.controller.PolaznikController;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.Polaznik;
import com.example.demo.service.PolaznikService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PolaznikControllerUnitTest {

    @InjectMocks
    private PolaznikController controller;

    @Mock
    private PolaznikService service;

    @Mock
    private EntityMapper mapper;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfPolaznikDTO() {
        Polaznik polaznik = new Polaznik();
        PolaznikDTO dto = new PolaznikDTO();
        when(service.getAll()).thenReturn(List.of(polaznik));
        when(mapper.toDTO(polaznik)).thenReturn(dto);

        ResponseEntity<List<PolaznikDTO>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(dto);
        verify(service).getAll();
        verify(mapper).toDTO(polaznik);
    }

    @Test
    void getById_ReturnsPolaznikDTO() {
        Long id = 1L;
        Polaznik polaznik = new Polaznik();
        PolaznikDTO dto = new PolaznikDTO();

        when(service.getById(id)).thenReturn(polaznik);
        when(mapper.toDTO(polaznik)).thenReturn(dto);

        ResponseEntity<PolaznikDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void create_ReturnsBadRequestIfBindingErrors() {
        PolaznikDTO dto = new PolaznikDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<PolaznikDTO> response = controller.create(dto, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verifyNoInteractions(service);
    }

    @Test
    void create_SavesPolaznikAndReturnsCreated() {
        PolaznikDTO dto = new PolaznikDTO();
        Polaznik polaznik = new Polaznik();
        Polaznik saved = new Polaznik();
        PolaznikDTO savedDto = new PolaznikDTO();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(mapper.toEntity(dto)).thenReturn(polaznik);
        when(service.save(polaznik)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(savedDto);

        ResponseEntity<PolaznikDTO> response = controller.create(dto, bindingResult);

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
