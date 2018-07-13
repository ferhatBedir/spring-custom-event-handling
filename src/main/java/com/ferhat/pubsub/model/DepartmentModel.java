package com.ferhat.pubsub.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Department Model Class")
public class DepartmentModel {

    @ApiModelProperty(value = "DepartmentName", example = "Resource And Development")
    private String departmentName;
}
