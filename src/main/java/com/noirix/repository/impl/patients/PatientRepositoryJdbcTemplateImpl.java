package com.noirix.repository.impl.patients;

import com.noirix.domain.DTO.PatientDTO;
import com.noirix.domain.Patient;
import com.noirix.repository.PatientRepository;
import com.noirix.repository.rowmapper.PatientRowMapper;
import com.noirix.repository.rowmapper.PatientRowMapperDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class PatientRepositoryJdbcTemplateImpl implements PatientRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PatientRowMapper patientRowMapper;
    private final PatientRowMapperDTO patientRowMapperDTO;

    @Override
    public Patient findById(Long id) {
        return jdbcTemplate.queryForObject("select * from patients where is_deleted = 'false' and card_number = " + id,
                patientRowMapper);
    }

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query("select * from patients where is_deleted = 'false' order by card_number desc",
                patientRowMapper);
    }

    @Override
    public Patient create(Patient patient) {
        jdbcTemplate.update("insert into patients(name, surname, gender, birthday_data, address, id_user, region_number, " +
                        "created, changed) values(?,?,?,?,?,?,?,?,?)",
                patient.getName(),
                patient.getSurname(),
                patient.getGender(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getIdUser(),
                patient.getRegionNumber(),
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));
        return patient;
    }

    @Override
    public Patient update(Long id, Patient patient) {
        jdbcTemplate.update("update patients set name = ?, surname = ?, gender = ?, birthday_data = ?, address = ?, " +
                        "id_user = ?, region_number = ?, changed = ? where card_number = ? and is_deleted = 'false'",
                patient.getName(),
                patient.getSurname(),
                patient.getGender(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getIdUser(),
                patient.getRegionNumber(),
                Timestamp.valueOf(LocalDateTime.now()),
                id);

        return findById(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("update patients set changed = ?, is_deleted = true where card_number = ?",
                Timestamp.valueOf(LocalDateTime.now()),
                id);
    }

    @Override
    public PatientDTO getNameAndSurnameByPhone(String phoneNumber) {
        return jdbcTemplate.queryForObject("select card_number, name, surname from patients inner join users u on " +
                "patients.id_user = u.id AND phone_number = '" + phoneNumber + "'", patientRowMapperDTO);
    }

    @Override
    public PatientDTO getAddressBySurname(String surname) {
        return jdbcTemplate.queryForObject("select * from address_by_surname('"+ surname +"')", patientRowMapperDTO);
    }
}