package lesson7.tests.negative;

import lesson7.pages.RegistrationPage;
import lesson7.tests.TestBase;
import org.junit.jupiter.api.Test;

public class EmptyRegistrationPageTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillWholeDataFormTest() {
        registrationPage.openPage()
                .submit();

        registrationPage.checkTableNotExist();
    }
}
