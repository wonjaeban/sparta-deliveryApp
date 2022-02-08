package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.RestaurantDto;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant saving(RestaurantDto requestDto) {
        if (requestDto.getMinOrderPrice() < 1000 || requestDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("허용주문가격이 아닙니다.");
        } else if (requestDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("100원 단위 입력이 아닙니다.");
        } else if (requestDto.getDeliveryFee() < 0 || requestDto.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("허용배달비가 아닙니다.");
        } else if (requestDto.getDeliveryFee() % 500 != 0) {
            throw new IllegalArgumentException("500원 단위 입력이 아닙니다.");
        } else {
            Restaurant restaurant = new Restaurant(requestDto);
            return restaurantRepository.save(restaurant);
        }
    }
}
