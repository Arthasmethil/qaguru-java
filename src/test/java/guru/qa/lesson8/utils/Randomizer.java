package guru.qa.lesson8.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class Randomizer {
    private static final Faker faker = new Faker(new Locale("en-GB"));

    public static String getFakerFirstName() {
        return faker.name().firstName();
    }

    public static String getFakerLastName() {
        return faker.name().lastName();
    }

    public static String getFakerEmail() {
        return faker.internet().emailAddress();
    }

    public static String getFakerAddress() {
        return faker.address().streetAddress();
    }

    public static String getFakerGender() {
        String[] gender = {"Male", "Female"};
        return faker.options().option(gender);
    }

    public static String getFakerUserNumber() {
        String code = String.valueOf(faker.number().numberBetween(900, 999));
        String number = String.valueOf(faker.number().numberBetween(1000000, 9999999));
        return code + number;
    }

    public static LocalDate getFakerDate() {
        Date dateOfBrith = faker.date().birthday();
        return dateOfBrith.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String getFakerSubjects() {
        String[] subjects = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology", "Computer Science",
                "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History", "Civics"
        };
        return faker.options().option(subjects);
    }

    public static String getFakerHobbies() {
        String[] hobbies = {"Sport", "Reading", "Music"};
        return faker.options().option(hobbies);
    }

    public static String getFakerState() {
        String[] hobbies = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return faker.options().option(hobbies);
    }

    public static String getFakerCity(String state) {
        switch (state) {
            case "NCR" -> {
                String[] cityOfNCR = {"Delhi", "Gurgaon", "Noida"};
                return faker.options().option(cityOfNCR);
            }
            case "Uttar Pradesh" -> {
                String[] cityOfUttarPradesh = {"Agra", "Lucknow", "Merrut"};
                return faker.options().option(cityOfUttarPradesh);
            }
            case "Haryana" -> {
                String[] cityOfHaryana = {"Karnal", "Panipat"};
                return faker.options().option(cityOfHaryana);
            }
            case "Rajasthan" -> {
                String[] cityOfRajasthan = {"Jaipur", "Jaiselmer"};
                return faker.options().option(cityOfRajasthan);
            }
            default -> {
                return "Unknown state";
            }
        }
    }
}




