package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.*;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.FoodOrder;
import com.sparta.mydelivery.model.Order;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodOrderRepository;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodOrderRepository foodorderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public  OrderReturnDto saving(OrderRequestDto requestDto) throws Exception {
        List<FoodsDto> foodOrders = new ArrayList<>();;
        OrderReturnDto orderReturnDto = new OrderReturnDto();
        List<FoodOrder> foodorders1 = new ArrayList<>();

        int totalPrice = 0;
        String restaurantName;
        for (int i = 0; i < requestDto.getFoods().size(); i++) {
            if (requestDto.getFoods().get(i).getQuantity() < 1 || requestDto.getFoods().get(i).getQuantity() > 100) {
                throw new IllegalArgumentException("허용수량이 아닙니다");
            } else {
                Optional<Food> foodNames = foodRepository.findById(requestDto.getFoods().get(i).getId());
                String foodName;
                int foodPrice;
                int foodPrices;
                if (foodNames.isPresent()) {
                    Food realFood = foodNames.get();
                    foodName = realFood.getName();
                    foodPrice = realFood.getPrice();
                    foodPrices = foodPrice * (requestDto.getFoods().get(i).getQuantity());
                    totalPrice += foodPrices;
                    FoodsDto fooddto1 = new FoodsDto();
                    fooddto1.setName(foodName);
                    fooddto1.setQuantity(requestDto.getFoods().get(i).getQuantity());
                    fooddto1.setPrice(foodPrices);
//                    반환해줄 음식명단.
                    foodOrders.add(fooddto1);
//                      나중에 주문 정보 불러오기 위한 DB저장. 자식 테이블 하나씩 완성.
                    FoodOrder foodOrder = new FoodOrder(fooddto1);
                    foodorderRepository.save(foodOrder);
                    foodorders1.add(foodOrder);
                }
            }
        }
//                      부모 테이블 완성.
                    Order order1 = new Order(requestDto, foodorders1);
                    orderRepository.save(order1);


                    Optional<Restaurant> restaurantNames = restaurantRepository.findById(requestDto.getRestaurantId());
                    if (restaurantNames.isPresent()) {
                        Restaurant restaurant = restaurantNames.get();
                        restaurantName = restaurant.getName();
                        int deliveryFee = restaurant.getDeliveryFee();
                        int minOrderPrice = restaurant.getMinOrderPrice();
                        if (totalPrice <= minOrderPrice) {
                            throw new IllegalArgumentException("최소주문가격 미만입니다.");
                        } else {
                            totalPrice += deliveryFee;
                            orderReturnDto.setRestaurantName(restaurantName);
                            orderReturnDto.setDeliveryFee(deliveryFee);
                            orderReturnDto.setTotalPrice(totalPrice);
                            orderReturnDto.setFoods(foodOrders);
                        }
                    }
                    else {
                        throw new Exception();
                    }
        return orderReturnDto;
    }


    public List<OrderReturnDto> finding() {
        List<Order> orders = orderRepository.findAll();
        List<OrderReturnDto> orderReturnDtos;

        for (int i = 0; i <orders.size(); i++) {
            OrderReturnDto orderReturnDto;
            orders.get(i).getRestaurantId();
            orderReturnDto.setRestaurantName();
        }

    }



}

