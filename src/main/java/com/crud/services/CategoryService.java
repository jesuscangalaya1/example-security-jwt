package com.crud.services;

import com.crud.dtos.request.CategoryRequest;
import com.crud.dtos.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryResponse> listCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);

}
