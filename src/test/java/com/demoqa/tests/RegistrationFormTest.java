package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import pages.RegistrationFormPage;

public class RegistrationFormTest {

    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            expectedFullName = String.format("%s %s", firstName, lastName),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            currentAddress = faker.address().fullAddress(),
            dayOfBirth = "11",
            monthOfBirth = "May",
            yearOfBirth = "1918",
            dateOfBirth = String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth),
            gender = "Male",
            subject1 = "Physics",
            hobby = "Music",
            picture = "images.jpg",
            state = "NCR",
            city = "Gurgaon",
            expectedCityAndState = String.format("%s %s", state, city),
            modalTitle = "Thanks for submitting the form";


    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() {
        registrationFormPage.openPage();
    }

    @Test
    void fillRegFormTest() {

        registrationFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(mobile)
                .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubject(subject1)
                .setHobby(hobby)
                .uploadImg(picture)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .pressSubmit();

        registrationFormPage.checkModalTitle(modalTitle)
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", dateOfBirth)
                .checkResult("Subjects", subject1)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedCityAndState);

    }


}
