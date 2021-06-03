package com.epam.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {

    private Integer id;
    @NotBlank
    private String name;

}
