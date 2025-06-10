package com.bitsandbites.service;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.mapper.CategoryMapper;
import com.bitsandbites.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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
public List<CategoryDto> gatAllCat(){
    List<CategoryDto> list = categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
    return list;
}
public CategoryDto getCategoryById(Long id){
    Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
    return CategoryMapper.toCategoryDto(category);

}
public String deleteById(Long id){
    categoryRepository.deleteById(id);
    return "Category"+ id + " Deleted";
}

}
