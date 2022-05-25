package com.mediscreen.patient.service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    /**
     * find a patient in db
     * @param id user id
     * @return patient data
     */
    public Optional<Patient> findById(int id) {
        return patientRepository.findById(id);
    }

    /**
     * find patients by family name in db
     * @param family family name
     * @return patients list
     */
    public List<Patient> getPatientsByFamily(String family) {
        return patientRepository.findAllByFamily(family);
    }

    public Patient getPatientById(int id) {
        Optional<Patient> patient = findById(id);
        return findById(id).orElseThrow(() -> new IllegalArgumentException("USER NOT FOUND"));
    }

    @Transactional
    public Patient updatePatient(int id, PatientDto dto) {
        Patient patient = getPatientById(id);
        patient.setFamily(dto.getFamily());
        patient.setGiven(dto.getGiven());
        patient.setDob(dto.getDob());
        patient.setSex(dto.getSex());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());

        return patient;
    }

}
