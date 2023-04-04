package com.noirix.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PatientCreateRequest {
    private String name;
    private String surname;
    private String gender;
    private Timestamp birthDate;
    private String address;
    private Long idUser;
    private Long regionNumber;
}
