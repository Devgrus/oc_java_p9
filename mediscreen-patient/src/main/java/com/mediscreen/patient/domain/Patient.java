package com.mediscreen.patient.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Family name is mandatory")
    private String family;

    @NotBlank(message = "Given name is mandatory")
    private String given;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Value sex is mandatory")
    @Pattern(regexp = "(^M$)|(^F$)", message = "Input only M or F")
    private String sex;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "phone number is mandatory")
    private String phone;
}
