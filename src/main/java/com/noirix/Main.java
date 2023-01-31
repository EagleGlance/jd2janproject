package com.noirix;

import org.apache.commons.lang3.RandomStringUtils;

public class Main {

    //Ctrl+Alt+O - import optimizing
    //Ctrl+Alt+L - formatting

    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(RandomStringUtils.random(10, true, true));
        System.out.println(RandomStringUtils.random(10, true, true));
        System.out.println(RandomStringUtils.random(20, true, true));

    }
}