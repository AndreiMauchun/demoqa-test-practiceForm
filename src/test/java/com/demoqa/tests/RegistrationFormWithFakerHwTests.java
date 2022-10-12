package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPages;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

    @Disabled
public class RegistrationFormWithFakerHwTests {
    RegistrationFormPages registrationFormPages = new RegistrationFormPages();
    Faker faker = new Faker();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "2560 x 1600";
    }

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String genter = ("Male");
    String number = faker.phoneNumber().subscriberNumber(10);
    String subject = ("English");
    String hobbies = ("Sports");
    String address = faker.address().fullAddress();





    @Test
    void fillFormTest() {
        registrationFormPages.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGenter(genter)
                .setNumber(number)
                .setSubject(subject)
                .setHobbies(hobbies)
                .setPicture("src/test/resources/img/bez-nazvanija.jpeg")
                .setAddress(address)
                .setStateAndCity("NCR", "Delhi")
                .setBirthDate("17", "September", "1991")
                .setSubmit();

         registrationFormPages.checkResultTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", number)
                .checkResult("Date of Birth", "17 September,1991")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "bez-nazvanija.jpeg")
                .checkResult("Address", address)
                .checkResult("State and City", "NCR Delhi");

    }
}
