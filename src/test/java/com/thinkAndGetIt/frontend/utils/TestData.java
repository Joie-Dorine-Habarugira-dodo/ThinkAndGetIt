package com.thinkAndGetIt.frontend.utils;

import com.github.javafaker.Faker;

public class TestData {
    private static final Faker faker = new Faker();

    public static String invalidEmail= ConfigReader.get("user.invalidEmail");
    public static String validPassword = ConfigReader.get("user.password");
    public static String invalidPassword= ConfigReader.get("user.invalidPassword");

    public static String generateFirstName() {
        return faker.name().firstName();
    }
    public static String generateLastName() {
        return faker.name().lastName();
    }
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

}
