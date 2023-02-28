package com.noirix.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomValuesGenerator {

    public String generateRandomString() {
        return RandomStringUtils.random(10, true, true);
    }

}
