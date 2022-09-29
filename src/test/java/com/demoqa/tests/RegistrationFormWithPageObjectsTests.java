package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormWithPageObjectsTests {
    RegistrationFormPages registrationFormPages = new RegistrationFormPages();


    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "2560 x 1600";
    }

    @Test
    void fillFormTest() {
        registrationFormPages.openPage()
                .setFirstName("Akim")
                .setLastName("Mir")
                .setEmail("Mir@gmail.ru")
                .setGenter("Male")
                .setNumber("12345678910")
                .setSubject("English")
                .setHobbies("Sports")
                .setPicture("src/test/resources/img/bez-nazvanija.jpeg")
                .setAddress("Some Address 14")
                .setStateAndCity("NCR", "Delhi")
                .setBirthDate("17", "September", "1991")
                .setSubmit();

        registrationFormPages.checkResultTableVisible()
                .checkResult("Student Name", "Akim Mir")
                .checkResult("Student Email", "Mir@gmail.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567891")
                .checkResult("Date of Birth", "17 September,1991")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "bez-nazvanija.jpeg")
                .checkResult("Address", "Some Address 14")
                .checkResult("State and City", "NCR Delhi");

    }
}
