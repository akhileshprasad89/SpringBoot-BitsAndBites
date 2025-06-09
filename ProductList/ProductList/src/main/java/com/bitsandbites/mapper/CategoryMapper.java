package com.bitsandbites.mapper;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;

public class CategoryMapper {
    public static Category toCategoryEntity(CategoryDto categoryDto){
        Category category=new Category();
        category.setName(categoryDto.getName());
        return category;
    }
    public static CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(category.getId());        ;
        categoryDto.setName(category.getName());
        categoryDto.setProduct(category.getProducts().stream()
                .map(ProductMapper::toProductDto).toList());
        return categoryDto;
    }
}
