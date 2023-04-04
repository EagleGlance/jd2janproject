package com.noirix.controller.mvc;

import com.noirix.controller.requests.PatientCreateRequest;
import com.noirix.domain.DTO.PatientDTO;
import com.noirix.domain.Patient;
import com.noirix.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest/patient")
@RequiredArgsConstructor
public class PatientRestController {

    private final PatientService patientService;
    private static final Logger log = Logger.getLogger(PatientRestController.class);

    private Long parsedPatientId(String id) {

        long parsedPatientId;

        try {
            parsedPatientId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Patient id: " + id + " cannot be parsed Long", e);
            parsedPatientId = 1L;
        }

        return parsedPatientId;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPatients()
    {
        List<Patient> patients = patientService.findAll();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findPatientById(@PathVariable String id) {

        Patient patient = patientService.findById(parsedPatientId(id));

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientCreateRequest request) {

        Patient patient = Patient.builder()
                .name((request.getName()))
                .surname(request.getSurname())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address((request.getAddress()))
                .idUser(request.getIdUser())
                .regionNumber(request.getRegionNumber())
                .build();

        Patient createdPatient = patientService.create(patient);

        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody PatientCreateRequest request) {

        Patient patient = Patient.builder()
                .name((request.getName()))
                .surname(request.getSurname())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address((request.getAddress()))
                .idUser(request.getIdUser())
                .regionNumber(request.getRegionNumber())
                .build();

        Patient createdPatient = patientService.update(parsedPatientId(id), patient);

        return new ResponseEntity<>(createdPatient, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/phone/{phone}")
    public ResponseEntity<Object> getNameAndSurnameByPhone(@PathVariable String phone) {

        PatientDTO patientDTO = patientService.getNameAndSurnameByPhone(phone);

        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/surname/{surname}")
    public ResponseEntity<Object> getAddressBySurname(@PathVariable String surname) {

        PatientDTO patientDTO = patientService.getAddressBySurname(surname);

        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }
}
