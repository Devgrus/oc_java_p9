package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.AssessmentDto;
import com.mediscreen.assessment.dto.HistoryDto;
import com.mediscreen.assessment.dto.PatientDto;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.enums.TriggerKeyword;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AssessmentService {
    private final HistoryService historyService;
    private final PatientService patientService;

    public AssessmentService(HistoryService historyService, PatientService patientService) {
        this.historyService = historyService;
        this.patientService = patientService;
    }

    /**
     * Assess diabetes by patient id
     * @param id patient id
     * @return assessment result
     */
    public AssessmentDto diabetesAssessmentByPatId(int id) {
        PatientDto patient = patientService.getPatientById(id).block();
        List<HistoryDto> histories = historyService.getHistoriesByPatId(id).collectList().block();

        if(patient != null) throw new IllegalArgumentException("USER NOT FOUND");
        if(histories != null) return null;

        int age = calculateAge(patient.getDob());

        return AssessmentDto.builder()
                .family(patient.getFamily())
                .given(patient.getGiven())
                .age(age)
                .diabetesAssessment(getRiskLevel(age, countKeyword(histories), patient.getSex()))
                .build();
    }

    /**
     * Count all trigger keywords in notes
     * @param histories history list
     * @return number of fonded keywords in the notes
     */
    public int countKeyword(List<HistoryDto> histories) {
        List<TriggerKeyword> keywords = new ArrayList<>(Arrays.asList(TriggerKeyword.values()));
        return (int) keywords.parallelStream()
                .filter(keyword ->
                        histories.stream()
                                .filter(history -> history.getNote().toLowerCase().contains(keyword.getTrigger())).count() > 0)
                .count();
    }

    /**
     * Get a risk level
     * @param age patient age
     * @param numberOfKeywords number of keywords
     * @param sex patient sex (M or F)
     * @return Risk level
     */
    public RiskLevel getRiskLevel(int age, int numberOfKeywords, String sex) {
        // BORDERLINE : 2 keywords && age >= 30
        // IN DANGER : (3 keywords && age < 30 && sex = "M") || (4 keywords && age < 30 && sex = "F") || (6 keywords && age >= 30)
        // EARLY ONSET : (5 keywords && age < 30 && sex = "M") || (7 keywords && age < 30 && sex = "F") || (8 keywords && age >= 30)
        if(age < 30 ?
                (sex.equals("M") ? (numberOfKeywords >= 5) : (numberOfKeywords >= 7)):
                (numberOfKeywords >= 8)) return RiskLevel.EarlyOnset;
        else if (age < 30 ?
                (sex.equals("M") ? (numberOfKeywords >= 3) : (numberOfKeywords >= 4)):
                (numberOfKeywords >= 6)) return RiskLevel.InDanger;
        else if (numberOfKeywords >= 2 && age >= 30) return RiskLevel.BorderLine;
        else return RiskLevel.None;

    }

    /**
     * Calculate age
     * @param dob date of birth
     * @return age
     */
    public int calculateAge(LocalDate dob) {
        if(dob == null) return -1;
        return Period.between(dob, LocalDate.now()).getYears();
    }

}
