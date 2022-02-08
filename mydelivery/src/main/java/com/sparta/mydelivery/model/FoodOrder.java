package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.FoodOrderDto;
import com.sparta.mydelivery.dto.FoodsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    public FoodOrder(String foodName, int quantity, int price) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }
    public FoodOrder(FoodsDto foodsDto) {
        this.foodName = foodsDto.getName();
        this.quantity = foodsDto.getQuantity();
        this.price = foodsDto.getPrice();
    }
}
