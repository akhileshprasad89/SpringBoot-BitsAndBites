package com.lcwd.electronic.store.ElectronicStore.dtos;

import com.lcwd.electronic.store.ElectronicStore.entities.OrderItem;
import com.lcwd.electronic.store.ElectronicStore.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDto {
    private String orderId;
    private String orderStatus="PENDING";
    private String paymentStatus="NOTPAID";
    private int orderAmount;
    private String billingAddres;
    private String billingPhone;
    private String billingName;
    private Date orderedDate=new Date();
    private Date deliveredDate;
    //private UserDto user;
    private List<OrderItemDto> orderItems=new ArrayList<>();
}
