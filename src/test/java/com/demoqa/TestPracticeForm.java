package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        //Configuration.browserSize = "1440x970";
    }

    @Test
    void fillFormTest() {

        open("/automation-practice-form");
        $("#firstName").setValue("Akim");
        $("#lastName").setValue("Mir");
        $("#userEmail").setValue("Mir@gmail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("12345678910");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day--017:not(.react-datepicker__day--selected").click();
        $("#subjectsInput").setValue("English").pressTab();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/img/bez-nazvanija.jpeg"));
        $("#currentAddress").setValue("Some Address 14");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Akim Mir"),

                        text("Mir@gmail.ru"),
                        text("Male"),
                        text("1234567891"),
                        text("17 September,1991"),
                        text("English"),
                        text("Sports"),
                        text("bez-nazvanija.jpeg"),
                        text("Some Address 14"),
                        text("NCR Delhi"));











    }
}
