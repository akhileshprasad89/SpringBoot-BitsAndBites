package com.bitsandbites.service;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.exception.CategoryAlreadyExitsException;
import com.bitsandbites.mapper.CategoryMapper;
import com.bitsandbites.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Optional<Category> byName = categoryRepository.findByName(categoryDto.getName());
        if (byName.isPresent()) {
            throw new CategoryAlreadyExitsException("Category already Exits " + categoryDto.getName());
        }
        Category categoryEntity = CategoryMapper.toCategoryEntity(categoryDto);
        Category save = categoryRepository.save(categoryEntity);
        CategoryDto categoryDto1 = CategoryMapper.toCategoryDto(save);
        return categoryDto1;
    }

    public List<CategoryDto> gatAllCat() {
        List<CategoryDto> list = categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
        return list;
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        return CategoryMapper.toCategoryDto(category);

    }

    public String deleteById(Long id) {
        categoryRepository.deleteById(id);
        return "Category" + id + " Deleted";
    }

}
