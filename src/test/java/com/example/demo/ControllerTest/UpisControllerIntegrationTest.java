package com.example.demo.ControllerTest;

import com.example.demo.DTO.PolaznikDTO;
import com.example.demo.DTO.ProgramObrazovanjaDTO;
import com.example.demo.DTO.UpisDTO;
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
class UpisControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createPolaznik() throws Exception {
        PolaznikDTO dto = new PolaznikDTO();
        dto.ime = "TestIme";
        dto.prezime = "TestPrezime";

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/polaznici")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(response, PolaznikDTO.class).id;
    }

    private Long createProgram() throws Exception {
        ProgramObrazovanjaDTO dto = new ProgramObrazovanjaDTO();
        dto.naziv = "Test Program";
        dto.csvet = 12;

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/obrazovanja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readValue(response, ProgramObrazovanjaDTO.class).id;
    }

    @Test
    void getAll_ShouldReturnArray() throws Exception {
        mockMvc.perform(get("/upisi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createAndGetById_ShouldWork() throws Exception {
        Long polaznikId = createPolaznik();
        Long programId = createProgram();

        UpisDTO dto = new UpisDTO();
        dto.polaznikId = polaznikId;
        dto.programObrazovanjaId = programId;

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/upisi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        UpisDTO created = objectMapper.readValue(response, UpisDTO.class);

        mockMvc.perform(get("/upisi/" + created.id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.polaznikId").value(polaznikId))
                .andExpect(jsonPath("$.programObrazovanjaId").value(programId));
    }

    @Test
    void create_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        UpisDTO dto = new UpisDTO(); // missing both IDs

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/upisi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_ShouldReturnNoContent() throws Exception {
        Long polaznikId = createPolaznik();
        Long programId = createProgram();

        UpisDTO dto = new UpisDTO();
        dto.polaznikId = polaznikId;
        dto.programObrazovanjaId = programId;

        String json = objectMapper.writeValueAsString(dto);

        String response = mockMvc.perform(post("/upisi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        UpisDTO created = objectMapper.readValue(response, UpisDTO.class);

        mockMvc.perform(delete("/upisi/" + created.id))
                .andExpect(status().isNoContent());
    }
}
