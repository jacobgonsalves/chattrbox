package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {

    @Id
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userPhoto;
    private String userCity;
    private String userState;
    private Integer userLocationTime;
    private List<String> userGroups;

    public User() {
        this.userGroups = new ArrayList<>();
        this.userLocationTime = 1;
    }
}
