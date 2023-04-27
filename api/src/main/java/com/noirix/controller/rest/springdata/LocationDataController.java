package com.noirix.controller.rest.springdata;

import com.noirix.domain.hibernate.HibernateLocation;
import com.noirix.repository.springdata.LocationsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/springdata/locations")
@RequiredArgsConstructor
public class LocationDataController {

    private final LocationsDataRepository locationsRepository;

    @GetMapping
    public ResponseEntity<Object> findAllVisibleLocations() {

        Map<String, List<HibernateLocation>> visibleLocations = Collections.singletonMap("result", locationsRepository.findByVisible(true));

        return new ResponseEntity<>(visibleLocations, HttpStatus.OK);

    }
}
