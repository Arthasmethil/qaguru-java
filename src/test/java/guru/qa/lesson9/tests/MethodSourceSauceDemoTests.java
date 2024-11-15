package guru.qa.lesson9.tests;

import guru.qa.lesson9.data.SauceDemoProduct;
import guru.qa.lesson9.data.User;
import guru.qa.lesson9.pages.SauceDemoBasketPage;
import guru.qa.lesson9.pages.SauceDemoCatalogPage;
import guru.qa.lesson9.pages.SauceDemoLoginPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static guru.qa.lesson9.data.SauceDemoProduct.*;

public class MethodSourceSauceDemoTests extends TestBase {

    SauceDemoCatalogPage sauceDemoCatalogPage = new SauceDemoCatalogPage();
    SauceDemoLoginPage loginPage = new SauceDemoLoginPage();
    SauceDemoBasketPage basketPage = new SauceDemoBasketPage();


    @Override
    void setup() {
        //override to avoid login
    }

    static Stream<Arguments> addSeveralItemsToBasketAndOpenIt() {
        return Stream.of(
                Arguments.of(
                        new SauceDemoProduct[] {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT},
                        new User("standard_user", "secret_sauce")
                ),
                Arguments.of(
                        new SauceDemoProduct[] {SAUCE_LABS_BOLT_T_SHIRT, SAUCE_LABS_FLEECE_JACKET, SAUCE_LABS_ONESIE},
                        new User("problem_user", "secret_sauce")
                )
        );
    }

    @MethodSource("addSeveralItemsToBasketAndOpenIt")
    @Tag("REGRESS")
    @Tag("WEB")
    @ParameterizedTest(name = "User {1} has in basket {0} products")
    void addSeveralItemsToBasketAndOpenIt(SauceDemoProduct[] products, User user) {
        loginPage
                .openPage()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .logIn();

        sauceDemoCatalogPage
                .addItemsToBasket(products)
                .goToBasket();

        basketPage.checkBasketNotEmpty();
    }
}
