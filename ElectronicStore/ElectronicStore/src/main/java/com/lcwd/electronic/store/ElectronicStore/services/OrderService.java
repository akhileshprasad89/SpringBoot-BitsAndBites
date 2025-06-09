package com.lcwd.electronic.store.ElectronicStore.services;

import com.lcwd.electronic.store.ElectronicStore.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.OrderDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(CreateOrderRequest orderDto);
    void removeOrder(String orderId);
    List<OrderDto> getOrdersOfUsers(String userId);
    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);


}
