package com.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dtos.request.PurchaseRequest;
import com.crud.dtos.response.Mensaje;
import com.crud.dtos.response.PurchaseResponse;
import com.crud.services.PurchaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
@CrossOrigin("*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> list() {
        return new ResponseEntity<>(purchaseService.getCustomerPurchases(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mensaje> purchaseProduct(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.purchaseProduct(purchaseRequest);
        return new ResponseEntity<>(new Mensaje("PURCHASE SUCCESSFULLY"), HttpStatus.CREATED);
    }
}
