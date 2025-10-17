package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.dto.FruitRequest;
import cat.itacademy.s04.t02.n02.dto.FruitResponse;
import cat.itacademy.s04.t02.n02.exceptions.DuplicateFruitException;
import cat.itacademy.s04.t02.n02.exceptions.NotFoundException;
import cat.itacademy.s04.t02.n02.services.FruitService;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FruitController.class)
class FruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FruitService fruitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_returns201_andLocationHeader() throws Exception {
        FruitRequest req = new FruitRequest("Apple", 5);
        FruitResponse resp = new FruitResponse(2L, "Apple", 5);

        when(fruitService.createFruit(any(FruitRequest.class))).thenReturn(resp);

        mockMvc.perform(post("/fruits-api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/fruits-api/2"))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    @Test
    void create_returns409_whenDuplicate() throws Exception {
        FruitRequest req = new FruitRequest("Banana", 10);

        when(fruitService.createFruit(any(FruitRequest.class))).thenThrow(new DuplicateFruitException("Fruit with name 'Banana' already exists"));

        mockMvc.perform(post("/fruits-api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Fruit with name 'Banana' already exists"));
    }

    @Test
    void getById_returns200_whenExists() throws Exception {
        when(fruitService.findById(1L)).thenReturn(new FruitResponse(1L, "Banana", 10));

        mockMvc.perform(get("/fruits-api/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Banana"));
    }

    @Test
    void getById_returns404_whenNotFound() throws Exception {
        when(fruitService.findById(99L)).thenThrow(new NotFoundException("Fruit with id 99 not found"));

        mockMvc.perform(get("/fruits-api/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Fruit with id 99 not found"));
    }

    @Test
    void getAll_returnsList() throws Exception {
        when(fruitService.findAll()).thenReturn(List.of(
                new FruitResponse(1L, "Banana", 10),
                new FruitResponse(2L, "Apple", 5)
        ));

        mockMvc.perform(get("/fruits-api/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void update_returns200_whenOk() throws Exception {
        FruitRequest req = new FruitRequest("Mango", 8);
        FruitResponse resp = new FruitResponse(5L, "Mango", 8);

        when(fruitService.update(eq(5L), any(FruitRequest.class))).thenReturn(resp);

        mockMvc.perform(put("/fruits-api/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Mango"));
    }

    @Test
    void delete_returns204_whenOk() throws Exception {
        Mockito.doNothing().when(fruitService).delete(3L);

        mockMvc.perform(delete("/fruits-api/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_returns404_whenNotFound() throws Exception {
        Mockito.doThrow(new NotFoundException("Fruit with id 99 not found"))
                .when(fruitService).delete(99L);

        mockMvc.perform(delete("/fruits-api/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void handleUnexpectedException_returns500() throws Exception {
        when(fruitService.findById(7L)).thenThrow(new RuntimeException("boom"));

        mockMvc.perform(get("/fruits-api/7"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Internal server error"));
    }

    @Test
    void create_returns400_whenInvalidRequest() throws Exception {
        FruitRequest invalid = new FruitRequest("", 5);

        mockMvc.perform(post("/fruits-api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }
}