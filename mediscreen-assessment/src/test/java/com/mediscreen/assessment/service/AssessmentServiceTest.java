package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.AssessmentDto;
import com.mediscreen.assessment.dto.HistoryDto;
import com.mediscreen.assessment.dto.PatientDto;
import com.mediscreen.assessment.enums.RiskLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    @Mock
    PatientService patientService;

    @Mock
    HistoryService historyService;

    @InjectMocks
    AssessmentService assessmentService;

    @Test
    public void assessmentById() {
        //given
        PatientDto patientDto = PatientDto.builder()
                .id(1)
                .family("ferguson")
                .given("lucas")
                .dob(LocalDate.of(1968,6,22))
                .sex("M")
                .address("2 Warren Street")
                .phone("387-866-1399")
                .build();

        HistoryDto historyDto = HistoryDto.builder()
                .id("asfsefhosiefnaosef")
                .patId(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .note("Anticorps fumeur cholestérol poids vertige Rechute Taille Microalbumine")
                .build();

        AssessmentDto result = AssessmentDto.builder()
                .family(patientDto.getFamily())
                .given(patientDto.getGiven())
                .age(Period.between(patientDto.getDob(), LocalDate.now()).getYears())
                .sex(patientDto.getSex())
                .diabetesAssessment(RiskLevel.EarlyOnset)
                .build();

        //when
        when(patientService.getPatientById(patientDto.getId())).thenReturn(Mono.just(patientDto));
        when(historyService.getHistoriesByPatId(patientDto.getId())).thenReturn(Flux.just(historyDto));

        //then
        assertThat(assessmentService.diabetesAssessmentByPatId(patientDto.getId())).isEqualTo(result);
    }

    @Test
    public void getRiskLevelTest() {
        //given

        //when

        //then
        assertThat(assessmentService.getRiskLevel(29, 6, "M")).isEqualTo(RiskLevel.EarlyOnset);
        assertThat(assessmentService.getRiskLevel(29, 7, "F")).isEqualTo(RiskLevel.EarlyOnset);
        assertThat(assessmentService.getRiskLevel(30, 8, "M")).isEqualTo(RiskLevel.EarlyOnset);
        assertThat(assessmentService.getRiskLevel(29, 4, "M")).isEqualTo(RiskLevel.InDanger);
        assertThat(assessmentService.getRiskLevel(28, 5, "F")).isEqualTo(RiskLevel.InDanger);
        assertThat(assessmentService.getRiskLevel(30, 6, "F")).isEqualTo(RiskLevel.InDanger);
        assertThat(assessmentService.getRiskLevel(31, 2, "F")).isEqualTo(RiskLevel.BorderLine);
        assertThat(assessmentService.getRiskLevel(29, 2, "F")).isEqualTo(RiskLevel.None);
    }

    @Test
    public void countKeywordTest() {
        //given
        List<HistoryDto> historyList = new ArrayList<>();
        HistoryDto history1 = HistoryDto.builder()
                .id("asfsefhosiefnaosea")
                .patId(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .note("Anticorps fumeur cholestérol poids vertige Rechute Taille Microalbumine")
                .build();
        HistoryDto history2 = HistoryDto.builder()
                .id("asfsefhosiefnaoseb")
                .patId(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .note("Good")
                .build();
        HistoryDto history3 = HistoryDto.builder()
                .id("asfsefhosiefnaosec")
                .patId(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .note("Anticorps fumeur cholestérol")
                .build();
        historyList.add(history1);
        historyList.add(history2);
        historyList.add(history3);

        //when

        //then
        assertThat(assessmentService.countKeyword(historyList)).isEqualTo(8);
    }

    @Test
    public void assessmentByFamily() {
        //given
        List<PatientDto> patients = new ArrayList<>();

        PatientDto patientDto = PatientDto.builder()
                .id(1)
                .family("ferguson")
                .given("lucas")
                .dob(LocalDate.of(1968,6,22))
                .sex("M")
                .address("2 Warren Street")
                .phone("387-866-1399")
                .build();

        patients.add(patientDto);

        HistoryDto historyDto = HistoryDto.builder()
                .id("asfsefhosiefnaosef")
                .patId(1)
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .note("Anticorps fumeur cholestérol poids vertige Rechute Taille Microalbumine")
                .build();

        List<AssessmentDto> assessmentList = new ArrayList<>();

        AssessmentDto assessment = AssessmentDto.builder()
                .family(patientDto.getFamily())
                .given(patientDto.getGiven())
                .age(Period.between(patientDto.getDob(), LocalDate.now()).getYears())
                .sex(patientDto.getSex())
                .diabetesAssessment(RiskLevel.EarlyOnset)
                .build();

        assessmentList.add(assessment);

        //when
        when(patientService.getPatientsByFamily(patientDto.getFamily())).thenReturn(Flux.just(patientDto));
        when(patientService.getPatientById(patientDto.getId())).thenReturn(Mono.just(patientDto));
        when(historyService.getHistoriesByPatId(patientDto.getId())).thenReturn(Flux.just(historyDto));

        //then
        assertThat(assessmentService.diabetesAssessmentByFamilyName(patientDto.getFamily())).isEqualTo(assessmentList);
    }
}
