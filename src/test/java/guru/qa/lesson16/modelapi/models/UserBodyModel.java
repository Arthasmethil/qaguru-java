package guru.qa.lesson16.modelapi.models;

import lombok.Data;

@Data
public class UserBodyModel {
    private String email;
    private String password;
}
