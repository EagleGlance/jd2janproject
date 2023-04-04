package com.noirix.repository.rowmapper;

import com.noirix.domain.DTO.PatientDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.PatientColumns.CARD_NUMBER;
import static com.noirix.repository.columns.PatientColumns.NAME;
import static com.noirix.repository.columns.PatientColumns.SURNAME;

@Component
public class PatientRowMapperDTO implements RowMapper<PatientDTO> {
    @Override
    public PatientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientDTO patientDTO;

        try {
            patientDTO = PatientDTO.builder()
                    .cardNumber(rs.getLong(CARD_NUMBER))
                    .name(rs.getString(NAME))
                    .surname(rs.getString(SURNAME))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patientDTO;
    }
}
