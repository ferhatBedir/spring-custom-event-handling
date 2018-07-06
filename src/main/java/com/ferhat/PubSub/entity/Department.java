package com.ferhat.PubSub.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Department {

    @Id
    private String departmentId;

    @NotNull
    @Size(min = 3, max = 40)
    private String departmentName;

    @NotNull
    private User user;

}
