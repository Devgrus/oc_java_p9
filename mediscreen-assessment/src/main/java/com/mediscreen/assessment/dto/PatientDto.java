package com.mediscreen.assessment.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
public class PatientDto {

    private Integer id;
    private String family;
    private String given;
    private LocalDate dob;
    private String sex;
    private String address;
    private String phone;
}
