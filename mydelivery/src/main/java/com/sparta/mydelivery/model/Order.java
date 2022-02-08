package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.FoodDto;
import com.sparta.mydelivery.dto.FoodsDto;
import com.sparta.mydelivery.dto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Table(name = "orders")
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<FoodOrder> foods;


    public Order(OrderRequestDto requestDto, List<FoodOrder> foods) {
        this.restaurantId = requestDto.getRestaurantId();
        this.foods = foods;
    }
}
