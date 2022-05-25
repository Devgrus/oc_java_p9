package com.mediscreen.patient.service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Add a new patient
     * @param patient new patient information
     * @return patient data
     */
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

}
