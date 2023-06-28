package com.crud.mapper;

import com.crud.dtos.request.CategoryRequest;
import com.crud.dtos.response.CategoryResponse;
import com.crud.entities.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface CategoryMapper {

    CategoryResponse toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    void updateCategoryFromDto(CategoryRequest categoryRequest, @MappingTarget CategoryEntity categoriaEntity);

    List<CategoryResponse> categoryListToCategoryDtoList(List<CategoryEntity> categoriaEntityList);

}
