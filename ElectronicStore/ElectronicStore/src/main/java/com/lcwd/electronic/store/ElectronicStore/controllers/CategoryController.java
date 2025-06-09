package com.lcwd.electronic.store.ElectronicStore.controllers;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.CategoryDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.ProductDto;
import com.lcwd.electronic.store.ElectronicStore.services.CategoryService;
import com.lcwd.electronic.store.ElectronicStore.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping
public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
    CategoryDto categoryDto1 = categoryService.create(categoryDto);
    return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
}
@PutMapping("/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody CategoryDto categoryDto){
    CategoryDto update = categoryService.update(categoryDto, categoryId);
    return new ResponseEntity<>(update,HttpStatus.OK);
}
@DeleteMapping("/{categoryId}")
public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId){
        categoryService.delete(categoryId);
    ApiResponseMessage categoryIsDeleteted = ApiResponseMessage.builder().message("Category is deleteted").status(HttpStatus.OK).success(true).build();
    return new ResponseEntity<>(categoryIsDeleteted,HttpStatus.OK);
}
@GetMapping
public ResponseEntity<PageableResponse<CategoryDto>> getAll(
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir

){
    PageableResponse<CategoryDto> all = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
    return new ResponseEntity<>(all,HttpStatus.OK);
}
@GetMapping("/{categoryId}")
public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId){
    CategoryDto categoryDto = categoryService.get(categoryId);
    return new ResponseEntity<>(categoryDto,HttpStatus.OK);
}
//create product with category
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> createProductWithCategory(
            @PathVariable("categoryId") String categoryId,
            @RequestBody ProductDto productDto
    ){
        ProductDto withCategory = productService.createWithCategory(productDto, categoryId);
        return new ResponseEntity<>(withCategory,HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateCategoryProduct(@PathVariable String productId,@PathVariable String categoryId){
        ProductDto productDto = productService.updateCategort(productId, categoryId);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>> getProductOFCategory(
            @PathVariable String categoryId,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<ProductDto> allOfCategory = productService.getAllOfCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allOfCategory,HttpStatus.OK);
    }
}
