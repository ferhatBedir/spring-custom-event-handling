package com.ferhat.pubsub.model;

import io.swagger.annotations.*;
import lombok.Data;

@Data
public class UserModel {

    private String userFirstName;
    private String userLastName;
    private String userEmail;
}
