package com.lcwd.electronic.store.ElectronicStore.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class AddItemToCartRequest {
    private String productId;
    private int quantity;
}
