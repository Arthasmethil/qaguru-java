package guru.qa.lesson16.modelapi.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    private String email;
    private String password;
}
