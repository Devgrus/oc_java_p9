package com.mediscreen.assessment.dto;


import com.mediscreen.assessment.enums.RiskLevel;
import lombok.*;

@Data
@Builder
public class AssessmentDto {
    private String family;
    private String given;
    private int age;
    private String sex;
    private RiskLevel diabetesAssessment;
}
