package guru.qa.lesson7.tests.positive;

import guru.qa.lesson7.pages.RegistrationPage;
import guru.qa.lesson7.tests.TestBase;
import org.junit.jupiter.api.Test;

public class RegistrationPageTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillWholeDataFormTest() {
        registrationPage.openPage()
                .setFirstName("Arthas")
                .setLastName("Menethil")
                .setEmail("lichking@blizzard.com")
                .setGender("Male")
                .setUserNumber("9990009343")
                .setDateOfBirth("01", "January", "1990")
                .setSubjectsInput("Maths")
                .setHobbiesWrapper("Music")
                .uploadPicture("guru_png.png")
                .setCurrentAddress("Frozen Throne")
                .setState("Uttar Pradesh")
                .setCity("Agra")
                .submit();

        registrationPage
                .checkTableResponse("Student Name", "Arthas Menethil")
                .checkTableResponse("Student Email", "lichking@blizzard.com")
                .checkTableResponse("Gender", "Male")
                .checkTableResponse("Mobile", "9990009343")
                .checkTableResponse("Date of Birth", "01 January,1990")
                .checkTableResponse("Subjects", "Maths")
                .checkTableResponse("Hobbies", "Music")
                .checkTableResponse("Picture", "guru_png.png")
                .checkTableResponse("Address", "Frozen Throne")
                .checkTableResponse("State and City", "Uttar Pradesh Agra");
    }

    @Test
    void fillMinimalDataFormTest() {
        registrationPage.openPage()
                .setFirstName("Arthas")
                .setLastName("Menethil")
                .setGender("Male")
                .setUserNumber("9990009343")
                .submit();

        registrationPage
                .checkTableResponse("Student Name", "Arthas Menethil")
                .checkTableResponse("Gender", "Male")
                .checkTableResponse("Mobile", "9990009343");
    }
}
