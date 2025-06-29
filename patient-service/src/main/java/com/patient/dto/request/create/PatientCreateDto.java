package com.patient.dto.request.create;

import com.patient.enums.Gender;
import com.patient.enums.InsuranceInfo;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientCreateDto {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    // TODO Add password, address and email field
    private String phone;

    private String email;

    private InsuranceInfo insuranceInfo;
}
