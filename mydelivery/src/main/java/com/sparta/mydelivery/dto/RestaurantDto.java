package com.sparta.mydelivery.dto;

import lombok.Getter;

@Getter
public class RestaurantDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
