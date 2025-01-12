package guru.qa.lesson16.modelapi.models;

import lombok.Data;

@Data
public class CreationUserResponseModel {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
