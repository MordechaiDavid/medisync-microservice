package com.patient.service;

import com.patient.entity.Patient;
import com.patient.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;


    // FIXME right now we can create unstop patient with same details (what is the diff key, id generate auto)
    // TODO check if try to create patient that already exist
    public Patient create(Patient patient) {
        Patient savedPatient = repository.save(patient);
        log.info("create new patient with id " + savedPatient.getId());
        return savedPatient;
    }

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Optional<Patient> getById(Long id) {
        return repository.findById(id);
    }

    public Patient update(Patient patient, Long id) {
        Patient existingPatient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException()); // FIXME what do we do with the exception?
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setInsuranceInfo(patient.getInsuranceInfo());
        existingPatient.setLastName(patient.getLastName());
        Patient updatedPatient = repository.save(existingPatient);
        log.info("updated patient with id: " + id);
        return updatedPatient;
    }

    public void delete(Long id) {
        repository.deleteById(id);
        log.info("patient with id " + id + " deleted");
    }


}

