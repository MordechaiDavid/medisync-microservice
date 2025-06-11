package com.medisync.appointments.entity;

import com.medisync.appointments.dto.request.create.AppointmentCreateDto;
import com.medisync.appointments.dto.request.update.AppointmentUpdateDto;
import com.medisync.appointments.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Appointment fromDto(AppointmentCreateDto dto) {
        return Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus())
                .build();
    }

    public static Appointment fromAppointmentDto(AppointmentUpdateDto dto) {
        return Appointment.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus())
                .build();
    }
}
