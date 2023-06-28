package com.crud.services;

import com.crud.dtos.request.ProductRequest;
import com.crud.dtos.response.PageableResponse;
import com.crud.dtos.response.ProductResponse;
import com.crud.entities.ProductEntity;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductResponse> listProducts();
    PageableResponse<ProductResponse> pageableProducts(int numeroDePagina,int medidaDePagina,String ordenarPor,String sortDir);
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);

    File exportDataExcel(List<ProductResponse> productResponses, String formato) throws Exception;

    ProductResponse createProductImage(MultipartFile image, String name, Double price, String description, Long categoryId) throws IOException;
    Resource getProductImage(Long id);

    ProductResponse updatedProductImage(Long id, MultipartFile image, String name, Double price, String description, Long categoryId) throws IOException;
}
