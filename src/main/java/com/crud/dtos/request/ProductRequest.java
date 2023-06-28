package com.crud.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private Double price;
    private String description;
    private Long categoryId;



}
