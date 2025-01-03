package guru.qa.lesson12.jenkins.tests;


import guru.qa.lesson12.jenkins.pages.RegistrationPage;
import guru.qa.lesson8.utils.Formatters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class RegistrationPageTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("remoteControlJenkins")
    @Tag("propertiesJenkins")
    void fillWholeDataFormWithAllureAndJenkinsTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .setDateOfBirth(testData.date)
                .setSubjectsInput(testData.subject)
                .setHobbiesWrapper(testData.hobby)
                .uploadPicture(testData.imageName)
                .setCurrentAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .submit();

        registrationPage
                .checkTableResponse("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponse("Student Email", testData.email)
                .checkTableResponse("Gender", testData.gender)
                .checkTableResponse("Mobile", testData.phoneNumber)
                .checkTableResponse("Date of Birth", testData.date.format(Formatters.dateFormatter))
                .checkTableResponse("Subjects", testData.subject)
                .checkTableResponse("Hobbies", testData.hobby)
                .checkTableResponse("Picture", testData.imageName)
                .checkTableResponse("Address", testData.address)
                .checkTableResponse("State and City", testData.state + " " + testData.city);
    }

    @Test
    @Tag("propertiesJenkins")
    void fillMinimalDataFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.phoneNumber)
                .submit();

        registrationPage
                .checkTableResponse("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponse("Gender", testData.gender)
                .checkTableResponse("Mobile", testData.phoneNumber);
    }
}
