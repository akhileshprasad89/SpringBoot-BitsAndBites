package com.lcwd.electronic.store.ElectronicStore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    private String cartId;
    private Date createdAt;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> items=new ArrayList<>();

}
