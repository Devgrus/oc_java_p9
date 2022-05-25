package com.mediscreen.patient.controller;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Add a new patient
     * @param dto new patient information
     * @return patient information
     */
    @PostMapping("/add")
    public ResponseEntity<PatientDto> addPatient(@Valid @RequestBody PatientDto dto) {
        Patient patient = patientService.addPatient(dto.toEntity());
        return ResponseEntity.status(HttpStatus.OK)
                .body(PatientDto.builder()
                        .family(patient.getFamily())
                        .given(patient.getGiven())
                        .sex(patient.getSex())
                        .dob(patient.getDob())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .build());
    }
}
