package com.sparta.mydelivery.dto;

import com.sparta.mydelivery.model.FoodOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderReturnDto {
    private String restaurantName;
    private List<FoodOrder> foods;
    private int deliveryFee;
    private int totalPrice;
}
