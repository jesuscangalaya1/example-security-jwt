package com.crud.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {

    private Long id;
    private Integer cantidad;
    private Double precio;
    private Double total;
    //private LocalDate fecha;
    private List<ProductResponse> products = new ArrayList<>();
}
