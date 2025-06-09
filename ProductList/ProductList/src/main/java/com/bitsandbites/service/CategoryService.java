package com.bitsandbites.service;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.mapper.CategoryMapper;
import com.bitsandbites.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

public CategoryDto createCategory(CategoryDto categoryDto){
    Category categoryEntity = CategoryMapper.toCategoryEntity(categoryDto);
    Category save = categoryRepository.save(categoryEntity);
    CategoryDto categoryDto1 = CategoryMapper.toCategoryDto(save);
    return categoryDto1;
}
}
