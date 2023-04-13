package com.noirix.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Counter {
    private int findById = 0;
    private int findAll = 0;
    private int create = 0;
    private int update = 0;
    private int delete = 0;
    private int emailAndPhoneNumber = 0;
    private int changedOverTime = 0;
}
