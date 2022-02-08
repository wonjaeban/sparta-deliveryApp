package com.sparta.mydelivery.model;

import com.sparta.mydelivery.dto.FoodDto;
import com.sparta.mydelivery.dto.FoodsDto;
import com.sparta.mydelivery.dto.OrderRequestDto;
import com.sparta.mydelivery.dto.OrderReturnDto;
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
    private String restaurantName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<FoodOrder> foods;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;


    public Order(OrderReturnDto orderReturnDto) {
        this.restaurantName = orderReturnDto.getRestaurantName();
        this.foods = orderReturnDto.getFoods();
        this.deliveryFee = orderReturnDto.getDeliveryFee();
        this.totalPrice = orderReturnDto.getTotalPrice();
    }
}
