package com.mediscreen.patient.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(status().isCreated());
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

    @Test
    public void getPatientsByFamilyTest() throws Exception {
        //given
        Patient patient1 = Patient.builder()
                .id(1)
                .family("FamilyTest")
                .given("GivenTest")
                .dob(LocalDate.of(1990,5,12))
                .address("Address Test")
                .sex("M")
                .phone("111-111-1111")
                .build();

        Patient patient2 = Patient.builder()
                .id(2)
                .family("FamilyTest")
                .given("GivenTest")
                .dob(LocalDate.of(1990,5,12))
                .address("Address Test")
                .sex("M")
                .phone("111-111-1111")
                .build();

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);

        //when
        when(patientService.getPatientsByFamily(patient1.getFamily())).thenReturn(patientList);

        //then
        mockMvc.perform(get("/patient?family=FamilyTest"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientByIdTest() throws Exception {
        //given
        Patient patient = Patient.builder()
                .id(1)
                .family("FamilyTest")
                .given("GivenTest")
                .dob(LocalDate.of(1990,5,12))
                .address("Address Test")
                .sex("M")
                .phone("111-111-1111")
                .build();

        //when
        when(patientService.getPatientById(patient.getId())).thenReturn(patient);

        //then
        mockMvc.perform(get("/patient/" + patient.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientByIdTestWithPatientNotExist() throws Exception {
        //given
        //when
        when(patientService.getPatientById(1)).thenThrow(new IllegalArgumentException("USER NOT FOUND"));

        //then
        mockMvc.perform(get("/patient/" + 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePatientTest() throws Exception {
        //given
        PatientDto dto1 = PatientDto.builder()
                .id(1)
                .family("Family")
                .given("Given")
                .dob(LocalDate.of(1992,5,12))
                .sex("F")
                .address("1 address")
                .phone("111-111-1111")
                .build();

        //when
        when(patientService.updatePatient(dto1.getId(), dto1)).thenReturn(dto1.toEntity());

        //then
        mockMvc.perform(put("/patient/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isOk());


    }

}
