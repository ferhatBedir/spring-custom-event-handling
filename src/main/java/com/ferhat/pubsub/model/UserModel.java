package com.ferhat.pubsub.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User Model Class")
public class UserModel {

    @ApiModelProperty(value = "UserFirstName", example = "Ahmet")
    private String userFirstName;

    @ApiModelProperty(value = "UserLastNAme", example = "Türk")
    private String userLastName;

    @ApiModelProperty(value = "UserEmail", example = "ahmet.türk@gmail.com")
    private String userEmail;
}
