package com.mediscreen.history.dto;

import com.mediscreen.history.domain.History;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class HistoryDto {
    private String id;

    @NotNull
    private Integer patId;

    @NotBlank
    private String note;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;

    public History toEntity() {
        return History.builder()
                .id(id)
                .patId(patId)
                .note(note)
                .build();
    }
}
