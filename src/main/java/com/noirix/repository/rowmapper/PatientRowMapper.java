package com.noirix.repository.rowmapper;

import com.noirix.domain.Patient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.PatientColumns.ADDRESS;
import static com.noirix.repository.columns.PatientColumns.BIRTH_DATE;
import static com.noirix.repository.columns.PatientColumns.CARD_NUMBER;
import static com.noirix.repository.columns.PatientColumns.CHANGED;
import static com.noirix.repository.columns.PatientColumns.CREATED;
import static com.noirix.repository.columns.PatientColumns.GENDER;
import static com.noirix.repository.columns.PatientColumns.ID_USER;
import static com.noirix.repository.columns.PatientColumns.NAME;
import static com.noirix.repository.columns.PatientColumns.REGION_NUMBER;
import static com.noirix.repository.columns.PatientColumns.SURNAME;

@Component
public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient;

        try {
            patient = Patient.builder()
                    .cardNumber(rs.getLong(CARD_NUMBER))
                    .name(rs.getString(NAME))
                    .surname(rs.getString(SURNAME))
                    .gender(rs.getString(GENDER))
                    .birthDate(rs.getTimestamp(BIRTH_DATE))
                    .address(rs.getString(ADDRESS))
                    .idUser(rs.getLong(ID_USER))
                    .regionNumber(rs.getLong(REGION_NUMBER))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }
}
