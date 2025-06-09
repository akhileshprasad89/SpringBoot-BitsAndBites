package com.lcwd.electronic.store.ElectronicStore.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class CreateOrderRequest {
    @NotBlank(message = "Cart is Required")
    private String cartId;
    @NotBlank(message = "User is Required")
    private String userId;
    private String orderStatus="PENDING";
    private String paymentStatus="NOTPAID";
    @NotBlank(message = "Addres is Required")
    private String billingAddres;
    @NotBlank(message = "Phone is Required")
    private String billingPhone;
    @NotBlank(message = "Billig Name id Required")
    private String billingName;

}
