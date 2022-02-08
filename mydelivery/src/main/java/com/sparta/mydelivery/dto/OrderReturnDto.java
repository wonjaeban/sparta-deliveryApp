package com.sparta.mydelivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderReturnDto {
    private String restaurantName;
    private List<FoodsDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
