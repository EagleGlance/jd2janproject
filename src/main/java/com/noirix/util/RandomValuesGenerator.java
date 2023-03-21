package com.noirix.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class RandomValuesGenerator {

    public String generateRandomString() {
        return RandomStringUtils.random(10, true, true);
    }

    public String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

}
