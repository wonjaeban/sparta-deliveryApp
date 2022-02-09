package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.RestaurantDto;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import com.sparta.mydelivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantDto requestDto) {
        return restaurantService.saving(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(@RequestParam int x, int y) {
        return restaurantService.finding(x, y);
    }
}
