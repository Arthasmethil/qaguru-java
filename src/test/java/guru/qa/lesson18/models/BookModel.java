package guru.qa.lesson18.models;

import lombok.Data;

@Data
public class BookModel {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publish_date;
    private String publisher;
    private String pages;
    private String description;
    private String website;
}
