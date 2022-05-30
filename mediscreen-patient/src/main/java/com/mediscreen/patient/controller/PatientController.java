package com.mediscreen.patient.controller;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PatientDto.builder()
                        .id(patient.getId())
                        .family(patient.getFamily())
                        .given(patient.getGiven())
                        .sex(patient.getSex())
                        .dob(patient.getDob())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .build());
    }

    /**
     * Get patients by family name
     * @param family family name
     * @return patient list
     */
    @GetMapping()
    public ResponseEntity<List<PatientDto>> getPatientsByFamily(@RequestParam("family") String family) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(patientService.getPatientsByFamily(family).stream().map(patient ->
                        PatientDto.builder()
                                .id(patient.getId())
                                .family(patient.getFamily())
                                .given(patient.getGiven())
                                .sex(patient.getSex())
                                .dob(patient.getDob())
                                .address(patient.getAddress())
                                .phone(patient.getPhone())
                                .build()).collect(Collectors.toList()));
    }

    /**
     * Get a patient by userId
     * @param id userId
     * @return patient information
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable int id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(PatientDto.builder()
                        .id(patient.getId())
                        .family(patient.getFamily())
                        .given(patient.getGiven())
                        .sex(patient.getSex())
                        .dob(patient.getDob())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .build());
    }

    /**
     * Update a patient
     * @param id patient id
     * @param dto patient informaiton
     * @return patient information
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable int id, @Valid @RequestBody PatientDto dto) {
        Patient patient = patientService.updatePatient(id, dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(PatientDto.builder()
                        .id(patient.getId())
                        .family(patient.getFamily())
                        .given(patient.getGiven())
                        .sex(patient.getSex())
                        .dob(patient.getDob())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .build());
    }

}
