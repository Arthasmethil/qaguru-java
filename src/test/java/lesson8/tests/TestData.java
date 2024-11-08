package lesson8.tests;

import lesson8.utils.Randomizer;

import java.time.LocalDate;

public class TestData {
    final String firstName = Randomizer.getFakerFirstName();
    final String lastName = Randomizer.getFakerLastName();
    final String email = Randomizer.getFakerEmail();
    final String address = Randomizer.getFakerAddress();
    final String gender = Randomizer.getFakerGender();
    final String phoneNumber = Randomizer.getFakerUserNumber();
    final LocalDate date = Randomizer.getFakerDate();
    final String subject = Randomizer.getFakerSubjects();
    final String hobby = Randomizer.getFakerHobbies();
    final String state = Randomizer.getFakerState();
    final String city = Randomizer.getFakerCity(state);
    final String imageName = "guru_png.png";
}