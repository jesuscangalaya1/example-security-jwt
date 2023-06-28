package com.crud.repositories;

import com.crud.entities.CategoryEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByDeletedFalse();

    
}
