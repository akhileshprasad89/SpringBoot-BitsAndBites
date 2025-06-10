package com.bitsandbites.service;

import com.bitsandbites.dto.ProductDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.entity.Product;
import com.bitsandbites.mapper.ProductMapper;
import com.bitsandbites.repository.CategoryRepository;
import com.bitsandbites.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductDto createProduct(ProductDto productDto){

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));
        Product productEntity = ProductMapper.toProductEntity(productDto, category);
        Product save = productRepository.save(productEntity);
        ProductDto productDto1 = ProductMapper.toProductDto(save);
        return productDto1;


    }
    public List<ProductDto> getAllProduct(){
       return productRepository.findAll().stream().map(e->ProductMapper.toProductDto(e)).toList();
    }
    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        return ProductMapper.toProductDto(product);
    }
    public ProductDto updateProduct(ProductDto productDto,Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDto(product);
    }
    public String deteteProduct(Long id){
        productRepository.deleteById(id);
        return "Product Has Been Deleted";

    }

}
