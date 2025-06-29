package com.patient.dto.response;

import com.patient.entity.Patient;
import com.patient.enums.Gender;
import com.patient.enums.InsuranceInfo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    // TODO Add address and email field
    private String phone;
    private String email;
    private InsuranceInfo insuranceInfo;
    private LocalDateTime createdAt;

    public static PatientResponseDto fromPatient(Patient patient){
        return PatientResponseDto.builder()
                .id(patient.getId())
                .createdAt(patient.getCreatedAt())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .insuranceInfo(patient.getInsuranceInfo())
                .email(patient.getEmail())
                .build();

    }

}


