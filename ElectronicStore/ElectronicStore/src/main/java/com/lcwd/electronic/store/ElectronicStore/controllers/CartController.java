package com.lcwd.electronic.store.ElectronicStore.controllers;

import com.lcwd.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.CartDto;
import com.lcwd.electronic.store.ElectronicStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable String userId,  @RequestBody AddItemToCartRequest addItemToCartRequest){
        CartDto cartDto = cartService.addItemToCart(userId, addItemToCartRequest);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(@PathVariable String userId, @PathVariable int itemId){
    cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage itemIsRemoved = ApiResponseMessage.builder()
                .message("Item is removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(itemIsRemoved,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId){
        cartService.clearCart(userId);
        ApiResponseMessage itemIsRemoved = ApiResponseMessage.builder()
                .message("Now Cart is Blank")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(itemIsRemoved,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId){
        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
