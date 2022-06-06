package com.mediscreen.patient.dto;

import com.mediscreen.patient.domain.Patient;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
public class PatientDto {

    private Integer id;
    @NotBlank(message = "Family name is mandatory")
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Special characters all not allowed")
    private String family;

    @NotBlank(message = "Given name is mandatory")
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ ]+$", message = "Special characters all not allowed")
    private String given;

    @NotNull(message = "Date of birth is mandatory")
    @PastOrPresent(message = "Only past or present date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Value sex is mandatory")
    @Pattern(regexp = "(^M$)|(^F$)", message = "Input only M or F")
    private String sex;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "phone number is mandatory")
    private String phone;

    public Patient toEntity() {
        return Patient.builder()
                .family(family == null ? null : family.toLowerCase())
                .given(given == null ? null : given.toLowerCase())
                .dob(dob)
                .sex(sex)
                .address(address)
                .phone(phone)
                .build();
    }
}
