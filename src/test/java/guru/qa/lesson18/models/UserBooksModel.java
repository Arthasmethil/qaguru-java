package guru.qa.lesson18.models;

import lombok.Data;

import java.util.List;

@Data
public class UserBooksModel {
    private String userId;
    private List<Isbn> collectionOfIsbns;

}
