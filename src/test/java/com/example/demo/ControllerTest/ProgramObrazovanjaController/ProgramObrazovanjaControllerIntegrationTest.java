package com.example.demo.ControllerTest.ProgramObrazovanjaController;


import com.example.demo.DTO.ProgramObrazovanjaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProgramObrazovanjaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_ShouldReturnArray() throws Exception {
        mockMvc.perform(get("/obrazovanja"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createAndGetById_ShouldWork() throws Exception {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        dto.naziv = "Frontend Developer";
        dto.csvet = 3;

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/obrazovanja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.naziv").value("Frontend Developer"))
                .andReturn().getResponse().getContentAsString();

        ProgramObrazovanjaDTO createdDto = objectMapper.readValue(response, ProgramObrazovanjaDTO.class);

        mockMvc.perform(get("/obrazovanja/" + createdDto.id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naziv").value("Frontend Developer"));
    }

    @Test
    void create_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        dto.naziv = ""; // Invalid

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/obrazovanja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_ShouldReturnNoContent() throws Exception {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        dto.naziv = "ZaBrisanje";
        dto.csvet = 3;
        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/obrazovanja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ProgramObrazovanjaDTO createdDto = objectMapper.readValue(response, ProgramObrazovanjaDTO.class);

        mockMvc.perform(delete("/obrazovanja/" + createdDto.id))
                .andExpect(status().isNoContent());
    }
}
