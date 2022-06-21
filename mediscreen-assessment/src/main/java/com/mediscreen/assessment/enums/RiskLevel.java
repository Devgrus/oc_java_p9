package com.mediscreen.assessment.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum RiskLevel {

    None("None"),
    BorderLine("Borderline"),
    InDanger("In Danger"),
    EarlyOnset("Early onset");

    private final String riskLevel;
}
