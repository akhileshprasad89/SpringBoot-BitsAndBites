package com.lcwd.electronic.store.ElectronicStore.controllers;

import com.lcwd.electronic.store.ElectronicStore.dtos.*;
import com.lcwd.electronic.store.ElectronicStore.services.FileService;
import com.lcwd.electronic.store.ElectronicStore.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private FileService fileService;
    @Value("${user.product.image.path}")
    private String imagePath;
    @Autowired
    ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto productDto){
        ProductDto update = productService.update(productDto, productId);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable String productId){
        productService.delete(productId);
        ApiResponseMessage productIsDeleted = ApiResponseMessage.builder().message("Product is Deleted").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(productIsDeleted,HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId){
        ProductDto single = productService.getSingle(productId);
        return new ResponseEntity<>(single,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<ProductDto> all = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<ProductDto> all = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<ProductDto> all = productService.searchByTitle(query,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage(@PathVariable String productId, @RequestParam("productImage") MultipartFile image) throws IOException {
        String imageName = fileService.uploadFile(image, imagePath);
        ProductDto single = productService.getSingle(productId);
        single.setProductImageName(imageName);
        ProductDto update = productService.update(single, productId);
        ImageResponse productImageUploaded = ImageResponse.builder().imageName(update.getProductImageName()).message("Product image Uploaded").status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(productImageUploaded,HttpStatus.CREATED);
    }

    @GetMapping("/image/{productId}")
    public void serveImage(@PathVariable("productId") String productId, HttpServletResponse response) throws IOException {
        ProductDto single = productService.getSingle(productId);
        InputStream resource = fileService.getResource(imagePath, single.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
