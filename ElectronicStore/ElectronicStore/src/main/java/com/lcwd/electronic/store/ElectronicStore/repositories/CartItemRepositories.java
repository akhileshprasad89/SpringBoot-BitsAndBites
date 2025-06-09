package com.lcwd.electronic.store.ElectronicStore.repositories;

import com.lcwd.electronic.store.ElectronicStore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepositories extends JpaRepository<CartItem, Integer> {
}
