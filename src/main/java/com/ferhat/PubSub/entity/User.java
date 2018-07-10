package com.ferhat.pubsub.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @ApiModelProperty(name = "User_Id")
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

    @NotNull
    private String departmentId;

    public User() {
    }


}
