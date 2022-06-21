package com.mediscreen.assessment.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class HistoryDto {
    private String id;
    private Integer patId;
    private String note;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
}
