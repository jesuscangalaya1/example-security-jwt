package com.crud.services;

import com.crud.dtos.request.PurchaseRequest;
import com.crud.dtos.response.PurchaseResponse;
import com.crud.entities.PurchaseEntity;

import java.util.List;

public interface PurchaseService {

    List<PurchaseResponse> getCustomerPurchases();
    void purchaseProduct(PurchaseRequest purchaseRequest);

}
