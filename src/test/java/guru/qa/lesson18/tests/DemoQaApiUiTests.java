package guru.qa.lesson18.tests;

import guru.qa.lesson18.api.bookstore.BooksApi;
import guru.qa.lesson18.helpers.WithLogin;
import guru.qa.lesson18.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.lesson18.constants.Constants.*;

public class DemoQaApiUiTests extends TestBase {

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
        profilePage.checkBookWasDeletedBook(BOOK_JAVASCRIPT.get("title"));
    }
}
