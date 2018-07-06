package com.ferhat.PubSub.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class User {

    @Id
    private String userId;

    @NotNull
    @Size(min = 3, max = 40)
    private String userFirstName;

    @NotNull
    @Size(min = 3, max = 40)
    private String userLastName;

    @NotNull
    private Integer userAge;

    @NotNull
    @Size(min = 3, max = 40)
    private String userEmail;

    public User() {
    }


}
