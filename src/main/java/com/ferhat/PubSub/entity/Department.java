package com.ferhat.pubsub.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class Department {

    @ApiModelProperty(name = "Department_Id")
    @Id
    private String departmentId;

    @NotNull
    @Size(min = 3, max = 40)
    private String departmentName;

    @ApiModelProperty(value = "User Entity")
    private User user;

}

