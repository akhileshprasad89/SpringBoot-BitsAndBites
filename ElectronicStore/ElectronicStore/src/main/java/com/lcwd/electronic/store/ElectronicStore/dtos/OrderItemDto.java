package com.lcwd.electronic.store.ElectronicStore.dtos;

import com.lcwd.electronic.store.ElectronicStore.entities.Order;
import com.lcwd.electronic.store.ElectronicStore.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDto {

    private int orderItemId;
    private int quantity;
    private int totalProce;
    private ProductDto product;

}
