package com.medisync.appointments.controller;


import com.medisync.appointments.dto.request.create.AppointmentCreateDto;
import com.medisync.appointments.dto.response.AppointmentResponseDto;
import com.medisync.appointments.entity.Appointment;
import com.medisync.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDto create(@RequestBody AppointmentCreateDto dto) {
        Appointment appointment = service.create(Appointment.fromDto(dto));
        return AppointmentResponseDto.fromAppointment(appointment);
    }

    @GetMapping
    public List<AppointmentResponseDto> getAll() {
        List<Appointment> appointments = service.getAll();
        return appointments.stream().map(AppointmentResponseDto::fromAppointment).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Appointment>> getById(@PathVariable Long id) {
        Optional<Appointment> optionalAppointment = service.getById(id);
        return new ResponseEntity<Optional<Appointment>>(optionalAppointment, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment appointment) {
        return new ResponseEntity<Appointment>(service.update(appointment, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>("Appointment deleted successfully with id: " + id, HttpStatus.OK);
    }


}

