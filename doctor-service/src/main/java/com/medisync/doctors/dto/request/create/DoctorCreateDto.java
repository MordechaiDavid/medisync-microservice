package com.medisync.doctors.dto.request.create;

import com.medisync.doctors.enums.DoctorTitle;
import com.medisync.doctors.enums.MedicalSpecialization;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorCreateDto {
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private DoctorTitle title;
    private String phone;
    private String email;
    private MedicalSpecialization specialization;


}
