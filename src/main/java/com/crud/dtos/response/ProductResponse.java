package com.crud.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private CategoryResponse category;
    private Integer image;

}
