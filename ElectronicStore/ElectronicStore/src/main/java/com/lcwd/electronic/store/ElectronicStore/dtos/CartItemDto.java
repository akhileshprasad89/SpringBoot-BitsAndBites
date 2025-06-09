package com.lcwd.electronic.store.ElectronicStore.dtos;

import com.lcwd.electronic.store.ElectronicStore.entities.Cart;
import com.lcwd.electronic.store.ElectronicStore.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private int catItemId;
    private ProductDto product;
    private int quantity;
    private int totalPrice;
}
