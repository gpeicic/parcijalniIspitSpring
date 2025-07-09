package com.example.demo.ControllerTest.UpisController;

import com.example.demo.controller.UpisController;
import com.example.demo.DTO.UpisDTO;
import com.example.demo.mapper.EntityMapper;
import com.example.demo.model.Polaznik;
import com.example.demo.model.ProgramObrazovanja;
import com.example.demo.model.Upis;
import com.example.demo.service.PolaznikService;
import com.example.demo.service.ProgramObrazovanjaService;
import com.example.demo.service.UpisService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpisControllerUnitTest {

    @InjectMocks
    private UpisController controller;

    @Mock
    private UpisService upisService;

    @Mock
    private PolaznikService polaznikService;

    @Mock
    private ProgramObrazovanjaService programObrazovanjaService;

    @Mock
    private EntityMapper mapper;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfUpisDTO() {
        Upis upis = new Upis();
        UpisDTO dto = new UpisDTO();

        when(upisService.getAll()).thenReturn(List.of(upis));
        when(mapper.toDTO(upis)).thenReturn(dto);

        ResponseEntity<List<UpisDTO>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(dto);
        verify(upisService).getAll();
        verify(mapper).toDTO(upis);
    }

    @Test
    void getById_ReturnsUpisDTO() {
        Long id = 1L;
        Upis upis = new Upis();
        UpisDTO dto = new UpisDTO();

        when(upisService.getById(id)).thenReturn(upis);
        when(mapper.toDTO(upis)).thenReturn(dto);

        ResponseEntity<UpisDTO> response = controller.getById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void create_ReturnsBadRequestIfBindingErrors() {
        UpisDTO dto = new UpisDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<UpisDTO> response = controller.create(dto, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verifyNoInteractions(upisService);
    }

    @Test
    void create_SavesUpisAndReturnsCreated() {
        UpisDTO dto = new UpisDTO();
        dto.polaznikId = 10L;
        dto.programObrazovanjaId = 20L;

        Polaznik polaznik = new Polaznik();
        ProgramObrazovanja program = new ProgramObrazovanja();
        Upis upis = new Upis();
        Upis saved = new Upis();
        UpisDTO savedDto = new UpisDTO();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(polaznikService.getById(10L)).thenReturn(polaznik);
        when(programObrazovanjaService.getById(20L)).thenReturn(program);
        when(mapper.toEntity(dto, polaznik, program)).thenReturn(upis);
        when(upisService.save(upis)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(savedDto);

        ResponseEntity<UpisDTO> response = controller.create(dto, bindingResult);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(savedDto);
    }

    @Test
    void delete_ReturnsNoContent() {
        Long id = 5L;

        ResponseEntity<Void> response = controller.delete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(upisService).delete(id);
    }
}
