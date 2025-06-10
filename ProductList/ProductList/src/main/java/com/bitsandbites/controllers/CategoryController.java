package com.bitsandbites.controllers;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @GetMapping
    public List<CategoryDto> getAllCategory(){
        List<CategoryDto> categoryDtos = categoryService.gatAllCat();
        return categoryDtos;
    }
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        CategoryDto categoryById = categoryService.getCategoryById(id);
        return categoryById;
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        String s = categoryService.deleteById(id);
        return s;
    }

}
