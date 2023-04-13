package com.noirix.service;

import com.noirix.domain.DTO.PatientDTO;
import com.noirix.domain.Patient;
import java.util.List;

public interface PatientService {
    Patient findById(Long id);

    List<Patient> findAll();

    Patient create(Patient object);

    Patient update(Long id, Patient object);

    void delete(Long id);

    PatientDTO getNameAndSurnameByPhone(String phoneNumber);
    PatientDTO  getAddressBySurname(String surname);
}
