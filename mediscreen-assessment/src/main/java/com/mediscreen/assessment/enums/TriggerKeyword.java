package com.mediscreen.assessment.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum TriggerKeyword {
    HEMOGLOBIEN_A1C("hémoglobine A1C"),
    MICROALBUMINE("microalbumine"),
    TAILLE("taille"),
    POIDS("poids"),
    FUMEUR("fumeur"),
    ANORMAL("anormal"),
    CHOLESTEROL("cholestérol"),
    VERTIGE("vertige"),
    RECHUTE("rechute"),
    REACTION("réaction"),
    ANTICORPS("anticorps");

    private final String trigger;
}
