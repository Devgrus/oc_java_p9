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
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getHistoriesTest() throws Exception {
        //given
        int patId = 1;

        History history1 = History.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .createdDate(LocalDate.of(2020, 4, 20))
                .build();

        History history2 = History.builder()
                .id("qwerqwer")
                .patId(1)
                .note("NOTE NOTE NOTE")
                .createdDate(LocalDate.of(2020, 6, 20))
                .build();

        List<History> histories = new ArrayList<>();
        histories.add(history1);
        histories.add(history2);

        //when
        when(historyService.getHistories(patId)).thenReturn(histories);

        //then
        mockMvc.perform(get("/patHistory?patId=" + patId))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHistoryTest() throws Exception {
        //given

        HistoryDto dto = HistoryDto.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE")
                .createdDate(LocalDate.of(2020, 4, 20))
                .build();

        //when
        when(historyService.updateHistory(dto.toEntity())).thenReturn(dto.toEntity());

        //then
        mockMvc.perform(put("/patHistory/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHistoryTestHistoryNotFound() throws Exception {
        //given
        HistoryDto dto = HistoryDto.builder()
                .id("asdfasdf")
                .patId(1)
                .note("NOTE NOTE")
                .createdDate(LocalDate.of(2020, 4, 20))
                .build();

        //when
        when(historyService.updateHistory(dto.toEntity())).thenThrow(new IllegalArgumentException("HISTORY NOT FOUND"));

        //then
        mockMvc.perform(put("/patHistory/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
