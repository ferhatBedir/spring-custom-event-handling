package com.ferhat.pubsub.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@ApiModel(value = "Department Entity Class")
public class Department {

    @ApiModelProperty(value = "Department_Id", example = "er457567we5568756")
    @Id
    private String departmentId;

    @ApiModelProperty(required = true, value = "departmentName", example = "Resource And Development", allowableValues = "[min = 3, max = 40]")
    @NotNull
    @Size(min = 3, max = 40)
    private String departmentName;

    @ApiModelProperty(value = "User Entity")
    private User user;

}

