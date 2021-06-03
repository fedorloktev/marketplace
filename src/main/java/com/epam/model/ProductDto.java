package com.epam.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductDto {

    private Integer id;
    @Positive
    private ClientDto userId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

}
