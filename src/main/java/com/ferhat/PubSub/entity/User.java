package com.ferhat.pubsub.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "User Entity Class")
public class User {

    @ApiModelProperty(value = "User_Id", example = "3e3457e3456457e")
    @Id
    private String userId;

    @ApiModelProperty(required = true, value = "UserFirstName", example = "Ahmet", allowableValues = "[min = 3, max = 40]")
    @NotNull
    @Size(min = 3, max = 40)
    private String userFirstName;

    @ApiModelProperty(required = true, value = "UserLastName", example = "Türk", allowableValues = "[min = 3, max = 40]")
    @NotNull
    @Size(min = 3, max = 40)
    private String userLastName;

    @ApiModelProperty(required = true, value = "UserAge", example = "25")
    @NotNull
    private Integer userAge;

    @ApiModelProperty(required = true, value = "UserEmail", example = "ahmet.türk@gmailcom")
    @NotNull
    @Size(min = 3, max = 40)
    private String userEmail;

    @ApiModelProperty(required = true, value = "UserDepartmentId", example = "e346e45736757r")
    @NotNull
    private String departmentId;

    public User() {
    }


}
