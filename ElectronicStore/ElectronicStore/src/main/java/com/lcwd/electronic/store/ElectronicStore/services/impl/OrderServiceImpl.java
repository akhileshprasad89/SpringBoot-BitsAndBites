package com.lcwd.electronic.store.ElectronicStore.services.impl;

import com.lcwd.electronic.store.ElectronicStore.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.OrderDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.entities.*;
import com.lcwd.electronic.store.ElectronicStore.exception.BadApiRequestException;
import com.lcwd.electronic.store.ElectronicStore.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.ElectronicStore.helper.Helper;
import com.lcwd.electronic.store.ElectronicStore.repositories.CartRepositories;
import com.lcwd.electronic.store.ElectronicStore.repositories.OrderRepository;
import com.lcwd.electronic.store.ElectronicStore.repositories.UserRepository;
import com.lcwd.electronic.store.ElectronicStore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartRepositories cartRepositories;
    @Override
    public OrderDto createOrder(CreateOrderRequest orderDto) {
        String userId=orderDto.getUserId();
        String cartId=orderDto.getCartId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Exits"));
        Cart cart = cartRepositories.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        List<CartItem> cartItems = cart.getItems();
        if(cartItems.size() <=0){
          throw new BadApiRequestException("Invalid number in cart");
        }
        Order order = Order.builder()
                .billingName(orderDto.getBillingName())
                .billingPhone(orderDto.getBillingPhone())
                .billingAddres(orderDto.getBillingAddres())
                .orderedDate(new Date())
                .deliveredDate(null)
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();
        AtomicReference<Integer> orderAmount=new AtomicReference<>(0);

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = OrderItem.builder()
                    .quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
                    .totalProce(cartItem.getQuantity() * cartItem.getProduct().getDiscountedPrice())
                    .order(order)
                    .build();
            orderAmount.set(orderAmount.get()+orderItem.getTotalProce());
            return orderItem;
        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount.get());
        cart.getItems().clear();
        cartRepositories.save(cart);
        Order saveOrder = orderRepository.save(order);
        return modelMapper.map(saveOrder,OrderDto.class);
    }

    @Override
    public void removeOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);

    }

    @Override
    public List<OrderDto> getOrdersOfUsers(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Order> orders = orderRepository.findByUser(user);
        List<OrderDto> orderDto = orders.stream().map((order -> modelMapper.map(order, OrderDto.class))).collect(Collectors.toList());
        return orderDto;
    }

    @Override
    public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Order> page = orderRepository.findAll(pageable);

        return Helper.getPageableResponse(page,OrderDto.class);
    }
}
