package com.noirix.service.impl.patient;

import com.noirix.domain.DTO.PatientDTO;
import com.noirix.domain.Patient;
import com.noirix.repository.PatientRepository;
import com.noirix.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient findById(Long id) {
        /*Validation layer*/
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        /*Validation layer*/
        return patientRepository.findAll();
    }

    @Override
    public Patient create(Patient object) {
        /*Validation layer*/
        return patientRepository.create(object);
    }

    @Override
    public Patient update(Long id, Patient object) {
        /*Validation layer*/
        return patientRepository.update(id, object);
    }

    @Override
    public void delete(Long id) {
        /*Validation layer*/
        patientRepository.delete(id);
    }

    @Override
    public PatientDTO getNameAndSurnameByPhone(String phoneNumber) {
        /*Validation layer*/
        return patientRepository.getNameAndSurnameByPhone(phoneNumber);
    }

    @Override
    public PatientDTO getAddressBySurname(String surname) {
        /*Validation layer*/
        return patientRepository.getAddressBySurname(surname);
    }


}
