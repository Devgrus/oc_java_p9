package com.mediscreen.patient.service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientService patientService;

    @Test
    public void addPatientTest() throws Exception {
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

        //when
        when(patientRepository.save(patient1)).thenReturn(patient1);

        //then
        assertThat(patientService.addPatient(patient1)).isEqualTo(patient1);
    }

    @Test
    public void findByIdTest() throws Exception {
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

        //when
        when(patientRepository.findById(patient1.getId())).thenReturn(Optional.of(patient1));

        //then
        assertThat(patientService.findById(patient1.getId()).get()).isEqualTo(patient1);
    }

    @Test
    public void findAllByFamilyTest() throws Exception {
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
        when(patientRepository.findAllByFamily(patient1.getFamily())).thenReturn(patientList);

        //then
        assertThat(patientService.getPatientsByFamily(patient1.getFamily()).size()).isEqualTo(patientList.size());
    }

    @Test
    public void getPatientByIdTest() throws Exception {
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

        //when
        when(patientRepository.findById(patient1.getId())).thenReturn(Optional.of(patient1));

        //then
        assertThat(patientService.getPatientById(patient1.getId())).isEqualTo(patient1);
    }
    @Test
    public void getPatientByIdTestPatientNotFound() throws Exception {
        //given

        //when
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> patientService.getPatientById(1)).hasMessage("USER NOT FOUND");
    }

}
