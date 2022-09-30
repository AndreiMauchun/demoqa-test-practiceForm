package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.demoqa.utils.RandomUtils.*;

public class TestPracticeFormWithFakerTests {

    Faker faker = new Faker();
    String firstName = faker.name().firstName(); // Emory
    String lastName = faker.name().lastName(); // Barton
    String email = faker.internet().emailAddress();
    String number = faker.phoneNumber().subscriberNumber(10);
    String subject = "English";
    String address = faker.address().fullAddress();
    String day = "17";
    String month = "September";
    String year = "1991";


    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "1440x970";
    }

    @Test
    void fillFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--selected").click();
        $("#subjectsInput").setValue(subject).pressTab();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/bez-nazvanija.jpeg"));

        $("#currentAddress").setValue(address);


        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click();


        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text(firstName), text(lastName),

                        text(email),
                        text("Male"),
                        text(number),
                        text(day + " " + month + "," + year),
                        text(subject),
                        text("Sports"),
                        text("bez-nazvanija.jpeg"),
                        text(address),
                        text("NCR Delhi"));

    }
}
