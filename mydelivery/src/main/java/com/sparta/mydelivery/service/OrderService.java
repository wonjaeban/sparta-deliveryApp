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
        List<FoodsDto> foodOrders = null;
        OrderReturnDto orderReturnDto = null;
        List<FoodOrder> foodorders1 = null;
        int totalPrice = 0;
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
                    foodPrices = foodPrice * requestDto.getFoods().get(i).getQuantity();
                    totalPrice += foodPrices;
                } else {
                    throw new Exception();
                }
                FoodsDto fooddto = null;
                fooddto.setName(foodName);
                fooddto.setQuantity(requestDto.getFoods().get(i).getQuantity());
                fooddto.setPrices(foodPrices);
                foodOrders = new ArrayList<>();
                foodOrders.add(fooddto);

                FoodOrder foodOrder = new FoodOrder(fooddto);
                FoodOrder FoodOrder1 = foodorderRepository.save(foodOrder);
                foodorders1 = null;
                foodorders1.add(FoodOrder1);
            }
        }
        Order order = new Order(requestDto, foodorders1);
        orderRepository.save(order);

        Optional<Restaurant> restaurantNames = restaurantRepository.findById(requestDto.getRestaurantId());
        if (restaurantNames.isPresent()) {
            Restaurant restaurant = restaurantNames.get();
            String restaurantName = restaurant.getName();
            int deliveryFee = restaurant.getDeliveryFee();
            totalPrice += deliveryFee;
            orderReturnDto.setRestaurantName(restaurantName);
            orderReturnDto.setDeliveryFee(deliveryFee);
            orderReturnDto.setTotalPrice(totalPrice);
        }
        else {
            throw new Exception();
        }
        orderReturnDto.setFoods(foodOrders);
        return orderReturnDto;





    }






    }

