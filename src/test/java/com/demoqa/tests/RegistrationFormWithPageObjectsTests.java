package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationFormPages;
import helps.Attach;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationFormWithPageObjectsTests {
    RegistrationFormPages registrationFormPages = new RegistrationFormPages();


    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    void fillFormTest() {
        step("Enter user data", () ->
        {
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
        });
        step("Checks form is correct", () -> {
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
        });
    }
}
