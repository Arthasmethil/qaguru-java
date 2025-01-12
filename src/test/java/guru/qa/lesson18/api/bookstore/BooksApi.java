package guru.qa.lesson18.api.bookstore;

import guru.qa.lesson18.api.auth.AuthorizationApi;
import guru.qa.lesson18.models.BookForDeleteModel;
import guru.qa.lesson18.models.BookModel;
import guru.qa.lesson18.models.Isbn;
import guru.qa.lesson18.models.UserBooksModel;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

import static guru.qa.lesson18.constants.Endpoints.BOOKS_ENDPOINT;
import static guru.qa.lesson18.constants.Endpoints.ONE_BOOK_ENDPOINT;
import static guru.qa.lesson18.specs.ApiSpecifications.*;
import static io.restassured.RestAssured.given;

public class BooksApi {
    @Step("Clear books before a Test")
    public static void clearBooksForUser() {
        given()
                .spec(getRequestSpecUserWithTokenAndId())
                .when()
                .delete(BOOKS_ENDPOINT)
                .then()
                .spec(deleteResponseSpec204);
    }

    @Step("Get a book by isbn")
    public static BookModel getBookByIsbn(String isbn) {
        return given()
                .spec(requestSpec)
                .queryParams("ISBN", isbn)
                .when()
                .get(ONE_BOOK_ENDPOINT)
                .then()
                .spec(successfulResponseSpec200)
                .extract().as(BookModel.class);
    }

    @Step("Add book for an User")
    public static void addBook(BookModel book) {
        UserBooksModel userBooksData = new UserBooksModel();
        List<Isbn> isbnList = new ArrayList<>();
        Isbn bookIsbn = new Isbn();

        userBooksData.setUserId(AuthorizationApi.authUserId);
        bookIsbn.setIsbn(book.getIsbn());
        isbnList.add(bookIsbn);
        userBooksData.setCollectionOfIsbns(isbnList);
        given()
                .spec(getRequestSpecAuthorizedUser())
                .body(userBooksData)
                .when()
                .post(BOOKS_ENDPOINT)
                .then()
                .spec(creationResponseSpec201);
    }

    @Step("Delete book for an User")
    public static ValidatableResponse deleteBook(String isbn) {
        BookForDeleteModel userDeleteBookData = new BookForDeleteModel();
        userDeleteBookData.setUserId(AuthorizationApi.authUserId);
        userDeleteBookData.setIsbn(isbn);
        return given()
                .spec(getRequestSpecAuthorizedUser())
                .body(userDeleteBookData)
                .when()
                .delete(ONE_BOOK_ENDPOINT)
                .then()
                .spec(responseOnlyWithLogs);
    }

    @Step("Delete book for an User")
    public static ValidatableResponse deleteBookWithoutToken(String isbn) {
        BookForDeleteModel userDeleteBookData = new BookForDeleteModel();
        userDeleteBookData.setUserId(AuthorizationApi.authUserId);
        userDeleteBookData.setIsbn(isbn);
        return given()
                .spec(requestSpec)
                .body(userDeleteBookData)
                .when()
                .delete(ONE_BOOK_ENDPOINT)
                .then()
                .spec(responseOnlyWithLogs);
    }
}
