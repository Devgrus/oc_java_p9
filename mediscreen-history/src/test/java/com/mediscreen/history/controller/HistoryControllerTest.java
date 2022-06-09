package com.mediscreen.history.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.history.domain.History;
import com.mediscreen.history.dto.HistoryDto;
import com.mediscreen.history.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    HistoryService historyService;

    @MockBean
    private MappingMongoConverter mappingMongoConverter;

    @Test
    public void addHistoryTest() throws Exception {
        //given
        HistoryDto dto = HistoryDto.builder()
                .patId(1)
                .note("HERE IS A NOTE")
                .build();

        History history = dto.toEntity();
        history.setId("asldkfjhalsdfhasdf");
        history.setCreatedDate(LocalDate.now());

        //when
        when(historyService.addHistory(dto.toEntity())).thenReturn(history);

        //then
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addHistoryTestWithBlankNote() throws Exception {
        //given
        HistoryDto dto = HistoryDto.builder()
                .patId(1)
                .note("")
                .build();

        History history = dto.toEntity();
        history.setId("asldkfjhalsdfhasdf");

        //when
        when(historyService.addHistory(dto.toEntity())).thenReturn(history);

        //then
        mockMvc.perform(post("/patHistory/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
