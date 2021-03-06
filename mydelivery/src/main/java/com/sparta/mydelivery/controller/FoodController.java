package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.FoodDto;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.model.UserRoleEnum;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import com.sparta.mydelivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    @Secured(value = UserRoleEnum.Authority.ADMIN)
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFoods(@RequestBody List<FoodDto> requestDto, @PathVariable Long restaurantId) {
        foodService.saving(requestDto, restaurantId);
    }

    @Secured(value = UserRoleEnum.Authority.USER)
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

}
