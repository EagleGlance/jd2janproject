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
public class UserCreateRequest {

    private String name;

    private String surname;

    private Timestamp birthDate;

    private String fullName;

    private Double weight;
}
