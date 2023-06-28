package com.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dtos.request.CategoryRequest;
import com.crud.dtos.response.CategoryResponse;
import com.crud.dtos.response.RestResponse;
import com.crud.services.CategoryService;
import com.crud.util.AppConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

        private final CategoryService categoryService;

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public RestResponse<List<CategoryResponse>> listCategories() {
                /*
                 * List<EntityModel<CategoryResponse>> categoryModels =
                 * categoryService.listCategories().stream()
                 * .map(categoryHateoasConfig::toModel).toList();
                 * 
                 * CollectionModel<EntityModel<CategoryResponse>> collectionModel =
                 * CollectionModel.of(categoryModels)
                 * .add(linkTo(methodOn(CategoryController.class).listCategories()).withSelfRel(
                 * ));
                 */
                return new RestResponse<>(AppConstants.SUCCESS,
                                String.valueOf(HttpStatus.OK),
                                "CATEGORIES SUCCESSFULLY READED",
                                categoryService.listCategories());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
                // return new RestResponse<>(AppConstants.SUCCESS,
                // String.valueOf(HttpStatus.OK),
                // AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY READED",
                // categoryService.getCategoryById(id));
                // categoryHateoasConfig.toModel(categoryService.getCategoryById(id)));
                return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        }

        // @PreAuthorize("hasRole('ADMIN')")
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public RestResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
                return new RestResponse<>(AppConstants.SUCCESS,
                                String.valueOf(HttpStatus.CREATED),
                                "CATEGORY SUCCESSFULLY CREATED",
                                categoryService.createCategory(categoryRequest));
                // categoryHateoasConfig.toModel(categoryService.createCategory(categoryRequest)));
        }

        @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public RestResponse<CategoryResponse> updatedCategory(@PathVariable Long id,
                        @RequestBody CategoryRequest categoryRequest) {
                return new RestResponse<>(AppConstants.SUCCESS,
                                String.valueOf(HttpStatus.OK),
                                AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY UPDATED",
                                categoryService.updateCategory(id, categoryRequest));
                // categoryHateoasConfig.toModel(categoryService.updateCategory(id,
                // categoryRequest)));
        }

        @DeleteMapping("/{id}")
        public RestResponse<String> deleteCategory(@PathVariable Long id) {
                categoryService.deleteCategory(id);
                return new RestResponse<>(AppConstants.SUCCESS,
                                String.valueOf(HttpStatus.OK),
                                AppConstants.MESSAGE_ID_CATEGORY + id + " SUCCESSFULLY DELETED",
                                "null"); // Data null.
        }

}
