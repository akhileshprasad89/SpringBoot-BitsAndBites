package com.lcwd.electronic.store.ElectronicStore.services.impl;

import com.lcwd.electronic.store.ElectronicStore.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.CartDto;
import com.lcwd.electronic.store.ElectronicStore.entities.Cart;
import com.lcwd.electronic.store.ElectronicStore.entities.CartItem;
import com.lcwd.electronic.store.ElectronicStore.entities.Product;
import com.lcwd.electronic.store.ElectronicStore.entities.User;
import com.lcwd.electronic.store.ElectronicStore.exception.BadApiRequestException;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.repositories.CartItemRepositories;
import com.lcwd.electronic.store.ElectronicStore.repositories.CartRepositories;
import com.lcwd.electronic.store.ElectronicStore.repositories.ProductRepository;
import com.lcwd.electronic.store.ElectronicStore.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStore.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepositories cartRepositories;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartItemRepositories cartItemRepositories;
    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        if(quantity<=0){
            throw new BadApiRequestException("Requested quantity is not valid");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProviderNotFoundException("Product is Not availabe"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Cart cart = null;
        try {
            cart=cartRepositories.findByUser(user).get();
        }catch (NoSuchElementException e){
            cart=new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        AtomicReference<Boolean> updated=new AtomicReference<>(false);

        List<CartItem> items = cart.getItems();
        List<CartItem> updatedItems = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(quantity);
                item.setTotalPrice(quantity*product.getDiscountedPrice());
                updated.set(true);
            }

            return item;
        }).collect(Collectors.toList());
cart.setItems(updatedItems);
        if(!updated.get()){
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountedPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }
    cart.setUser(user);
        Cart updatedCart = cartRepositories.save(cart);

        return mapper.map(updatedCart,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {

        CartItem cartItem1 = cartItemRepositories.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Cart Item Not found"));
        cartItemRepositories.delete(cartItem1);
    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Cart cart = cartRepositories.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given use not found"));
        cart.getItems().clear();
        cartRepositories.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Cart cart = cartRepositories.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart of given use not found"));
        return mapper.map(cart,CartDto.class);
    }
}
