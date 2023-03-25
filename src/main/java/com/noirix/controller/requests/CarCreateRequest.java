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
public class CarCreateRequest {

    private Long id;
    private String name;
    private String brand;
    private Float price;
    private Long user_id;
    private Timestamp created;
    private Timestamp changed;
}

