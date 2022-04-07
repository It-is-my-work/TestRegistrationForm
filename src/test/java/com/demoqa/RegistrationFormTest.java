package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTest {

    String firstName = "Richard",
            lastName = "Feynman",
            email = "quantum@Dodge.van",
            mobile = "1234567890",
            dayOfBirth = "11",
            monthOfBirth = "May",
            yearOfBirth = "1918",
            gender = "Male",
            subject1 = "Physics",
            subject2 = "Maths",
            hobby = "Music",
            picture = "images.jpg",
            currentAddress = "924 Circle Dr, Los Alamos, NM 87544, USA",
            state = "NCR",
            city = "Gurgaon";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage(){
        open("/automation-practice-form");
    }

    @Test
    void fillRegFormTest() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#userNumber").setValue(mobile);
        $("#genterWrapper").$(byText(gender)).click();
        fillDateOfBirth();
        $("#subjectsInput").setValue(subject1).pressEnter()
                .setValue(subject2).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(currentAddress);
        $("#submit").scrollTo();
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();
    }

    void fillDateOfBirth(){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText(monthOfBirth)).click();
        $(".react-datepicker__year-select").click();
        $(byText(yearOfBirth)).click();
        $(".react-datepicker__month").$(byText(dayOfBirth)).click();
    }
}