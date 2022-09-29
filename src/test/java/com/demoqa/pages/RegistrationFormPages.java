package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsTableComponent;
import com.demoqa.pages.components.StateAndCityComponent;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPages {
    private StateAndCityComponent stateAndCityComponent = new StateAndCityComponent();
    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail");

    public RegistrationFormPages openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPages setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;

    }
    public RegistrationFormPages setLastName(String value) {
        lastNameInput.setValue(value);

        return this;

    }
    public RegistrationFormPages setEmail(String value) {
        emailInput.setValue(value);

        return this;

    }
    public RegistrationFormPages setGenter(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;

    }
    public RegistrationFormPages setNumber(String value) {
        $("#userNumber").setValue(value);

        return this;

    }
    public RegistrationFormPages setSubject(String value) {
        $("#subjectsInput").setValue(value).pressTab();

        return this;

    }
    public RegistrationFormPages setHobbies(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;

    }
    public RegistrationFormPages setPicture(String value) {
        $("#uploadPicture").uploadFile(new File(value));

        return this;

    }
    public RegistrationFormPages setAddress(String value) {
        $("#currentAddress").setValue(value);

        return this;

    }
    public RegistrationFormPages setStateAndCity(String state, String city) {
        $("#state").click();
        stateAndCityComponent.setStateCity("NCR", "Delhi");

        return this;

    }
    public RegistrationFormPages setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();

        calendarComponent.setDate(day, month, year);

        return this;

    }
    public RegistrationFormPages setSubmit() {
        $("#submit").click();

        return this;

    }
    public RegistrationFormPages checkResultTableVisible() {
       resultsTableComponent.checkVisible();

       return this;

    }

    public RegistrationFormPages checkResult(String key, String value) {
        resultsTableComponent.checkResult(key, value);

        return this;
    }

}
