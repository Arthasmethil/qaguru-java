package guru.qa.lesson16.modelapi.models;

import lombok.Data;

@Data
public class RegistrationBodyModel {
    private String email;
    private String password;
}
