package com.medisync.dto.request.create;

import com.medisync.enums.DoctorTitle;
import com.medisync.enums.MedicalSpecialization;
import lombok.*;

@Data
@Getter
@Setter
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
