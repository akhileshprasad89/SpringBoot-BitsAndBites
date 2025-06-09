package com.lcwd.electronic.store.ElectronicStore.controllers;

import com.lcwd.electronic.store.ElectronicStore.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.ElectronicStore.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.ElectronicStore.dtos.OrderDto;
import com.lcwd.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.lcwd.electronic.store.ElectronicStore.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
  public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request){
      OrderDto order = orderService.createOrder(request);
      return new ResponseEntity<>(order, HttpStatus.CREATED);
  }
  @DeleteMapping("/{orderId}")
  public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId){
    orderService.removeOrder(orderId);
      ApiResponseMessage orderIsRemove = ApiResponseMessage.builder().status(HttpStatus.OK).message("Order is Remove").success(true).build();
      return new ResponseEntity<>(orderIsRemove,HttpStatus.OK);
  }
  @GetMapping("/users/{userId}")
  public ResponseEntity<List<OrderDto>> getOrdersOfUsers(@PathVariable String userId){
      List<OrderDto> ordersOfUsers = orderService.getOrdersOfUsers(userId);
      return new ResponseEntity<>(ordersOfUsers,HttpStatus.OK);
  }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
    @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
    @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir

    ){
        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
