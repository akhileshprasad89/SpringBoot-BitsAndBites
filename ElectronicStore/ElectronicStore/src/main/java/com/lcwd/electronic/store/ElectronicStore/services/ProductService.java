package com.lcwd.electronic.store.ElectronicStore.services;

import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,String productId);
    void delete(String productId);
    ProductDto getSingle(String productId);
    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);
    //create product with category
    ProductDto createWithCategory(ProductDto productDto, String categoryId);
    ProductDto updateCategort(String productId,String categoryId);
    PageableResponse<ProductDto> getAllOfCategory(String category,int pageNumber,int pageSize,String sortBy,String sortDir);
}
