package com.lcwd.electronic.store.ElectronicStore.services;

import com.lcwd.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.CartDto;

public interface CartService {
    CartDto addItemToCart(String userId, AddItemToCartRequest request);
        void removeItemFromCart(String userId,int cartItem);
        void clearCart(String userId);
        CartDto getCartByUser(String userId);
}
