package com.demoqa.pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateAndCityComponent {



    public StateAndCityComponent setStateCity(String state, String city) {
        $("#stateCity-wrapper").$(byText(state)).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        return this;

    }
}
