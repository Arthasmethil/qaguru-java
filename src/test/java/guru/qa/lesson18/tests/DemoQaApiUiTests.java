package guru.qa.lesson18.tests;

import guru.qa.lesson18.api.bookstore.BooksApi;
import guru.qa.lesson18.helpers.WithLogin;
import guru.qa.lesson18.models.ErrorModel;
import guru.qa.lesson18.pages.ProfilePage;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.lesson18.constants.Constants.*;

public class DemoQaApiUiTests extends TestBase {

    @DisplayName("Delete book with wrong ISBN")
    @Tag("API_UI")
    @Test
    void deleteBookWithWrongIsbn() {
        BooksApi.clearBooksForUser();

        BooksApi.addBook(BooksApi.getBookByIsbn(BOOK_JAVASCRIPT.get("isbn")));

        ValidatableResponse deletionResponse = BooksApi.deleteBook(WRONG_ISBN);

        int deleteSameBookStatusCode = deletionResponse.extract().statusCode();
        Assertions.assertThat(deleteSameBookStatusCode).isEqualTo(BAD_REQUEST);

        ErrorModel error = deletionResponse.extract().as(ErrorModel.class);
        Assertions.assertThat(error.getCode()).isEqualTo(ERROR_CODE_BAD_REQUEST);
        Assertions.assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE_BAD_REQUEST);
    }

    @DisplayName("Delete book that was deleted before")
    @Tag("API_UI")
    @Test
    void tryDeleteWithoutUserTokenTest() {
        BooksApi.clearBooksForUser();

        BooksApi.addBook(
                BooksApi.getBookByIsbn(BOOK_JAVASCRIPT.get("isbn"))
        );

        ValidatableResponse deletionResponse = BooksApi.deleteBookWithoutToken(BOOK_JAVASCRIPT.get("isbn"));

        ErrorModel error = deletionResponse.extract().as(ErrorModel.class);
        Assertions.assertThat(deletionResponse.extract().statusCode()).isEqualTo(UNAUTHORIZED);
        Assertions.assertThat(error.getCode()).isEqualTo(ERROR_CODE_UNAUTHORIZED);
        Assertions.assertThat(error.getMessage()).isEqualTo(ERROR_MESSAGE_UNAUTHORIZED);
    }

    @DisplayName("Add one book to an user")
    @Tag("API_UI")
    @WithLogin
    @Test
    void addBookToCollectionWITHLOGINTest() {
        BooksApi.clearBooksForUser();
        BooksApi.addBook(
                BooksApi.getBookByIsbn(BOOK_JAVASCRIPT.get("isbn"))
        );

        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfilePage();
        profilePage.checkAddedBook(BOOK_JAVASCRIPT.get("title"));
    }

    @DisplayName("Delete one book in a Collection")
    @Tag("API_UI")
    @WithLogin
    @Test
    void deleteOneBookInCollectionTest() {
        BooksApi.clearBooksForUser();

        BooksApi.addBook(BooksApi.getBookByIsbn(BOOK_JAVASCRIPT.get("isbn")));
        BooksApi.addBook(BooksApi.getBookByIsbn(BOOK_JS.get("isbn")));
        BooksApi.addBook(BooksApi.getBookByIsbn(BOOK_GIT.get("isbn")));

        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfilePage();
        profilePage.removeBanner();
        profilePage.checkAddedBook(BOOK_JAVASCRIPT.get("title"));
        profilePage.checkAddedBook(BOOK_JS.get("title"));
        profilePage.checkAddedBook(BOOK_GIT.get("title"));
        profilePage.deleteByTitle(BOOK_JAVASCRIPT.get("title"));
        profilePage.pageRefresh();
        profilePage.checkBookWasDeletedBook(BOOK_JAVASCRIPT.get("title"));
    }
}
