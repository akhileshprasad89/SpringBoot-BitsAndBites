package com.bitsandbites.controllers;

import com.bitsandbites.dto.ProductDto;
import com.bitsandbites.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto product = productService.createProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @GetMapping
    public List<ProductDto> getAllProduct(){
       return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public ProductDto getSingleProduct(@PathVariable long id){
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id){
       return productService.updateProduct(productDto,id);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
       return productService.deteteProduct(id);
    }
}
