package com.epam.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private Integer id;
    private String fullName;
    private String address;
    @NotBlank
    @Size(min = 1, max = 30)
    private String login;
    @NotBlank
    @Size(min = 1, max = 200)
    private String password;
    private RoleDto role;

}
