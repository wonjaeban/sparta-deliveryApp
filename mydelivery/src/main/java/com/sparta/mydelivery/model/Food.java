package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.FoodDto;
import com.sparta.mydelivery.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Food(Long restaurantId, String name, int price) {
        this.name = name;
        this.restaurantId = restaurantId;
        this.price = price;
    }
    public Food(FoodDto requestDto, Long restaurantId) {
        this.name = requestDto.getName();
        this.restaurantId = restaurantId;
        this.price = requestDto.getPrice();
    }
}
