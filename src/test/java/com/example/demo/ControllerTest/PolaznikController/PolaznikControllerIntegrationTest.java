package com.example.demo.ControllerTest.PolaznikController;


import com.example.demo.DTO.PolaznikDTO;
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
class PolaznikControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_ShouldReturnEmptyListInitially() throws Exception {
        mockMvc.perform(get("/polaznici"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createAndGetById_ShouldWork() throws Exception {
        PolaznikDTO dto = new PolaznikDTO();
        dto.ime = "Gabi";
        dto.prezime = "Zabi";


        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/polaznici")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ime").value("Gabi"))
                .andExpect(jsonPath("$.prezime").value("Zabi"))
                .andReturn().getResponse().getContentAsString();

        PolaznikDTO createdDto = objectMapper.readValue(response, PolaznikDTO.class);


        mockMvc.perform(get("/polaznici/" + createdDto.id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ime").value("Gabi"))
                .andExpect(jsonPath("$.prezime").value("Zabi"));
    }

    @Test
    void create_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        PolaznikDTO dto = new PolaznikDTO();
        dto.ime = "";
        dto.prezime = null;

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/polaznici")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_ShouldReturnNoContent() throws Exception {
        PolaznikDTO dto = new PolaznikDTO();
        dto.ime = "ZaBrisati";
        dto.prezime = "Test";

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/polaznici")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        PolaznikDTO createdDto = objectMapper.readValue(response, PolaznikDTO.class);

        mockMvc.perform(delete("/polaznici/" + createdDto.id))
                .andExpect(status().isNoContent());
    }
}
