package com.mediscreen.patient.service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientService patientService;

    @Test
    public void addPatientTest() {
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

}
