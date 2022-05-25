package com.mediscreen.patient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PatientService patientService;

    @Test
    public void addPatientTest() throws Exception {
        //given
        PatientDto dto1 = PatientDto.builder()
                .family("Family")
                .given("Given")
                .dob(LocalDate.of(1992, 2, 15))
                .sex("F")
                .address("1 address")
                .phone("111-111-1111")
                .build();

        //when
        when(patientService.addPatient(dto1.toEntity())).thenReturn(dto1.toEntity());

        //then
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPatientTestWithFutureDate() throws Exception {
        //given
        PatientDto dto1 = PatientDto.builder()
                .family("Family")
                .given("Given")
                .dob(LocalDate.now().plusDays(1))
                .sex("F")
                .address("1 address")
                .phone("111-111-1111")
                .build();

        //when
        when(patientService.addPatient(dto1.toEntity())).thenReturn(dto1.toEntity());

        //then
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPatientTestWithWrongSexValue() throws Exception {
        //given
        PatientDto dto1 = PatientDto.builder()
                .family("Family")
                .given("Given")
                .dob(LocalDate.of(1992,5,12))
                .sex("x")
                .address("1 address")
                .phone("111-111-1111")
                .build();

        //when
        when(patientService.addPatient(dto1.toEntity())).thenReturn(dto1.toEntity());

        //then
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPatientTestWithEmptyValues() throws Exception {
        //given
        PatientDto dto1 = PatientDto.builder()
                .family(null)
                .given(null)
                .dob(null)
                .sex(null)
                .address(null)
                .phone(null)
                .build();

        //when
        when(patientService.addPatient(dto1.toEntity())).thenReturn(dto1.toEntity());

        //then
        mockMvc.perform(post("/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isBadRequest());
    }

}
