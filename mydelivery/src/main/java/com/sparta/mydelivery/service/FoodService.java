package com.sparta.mydelivery.service;

import com.sparta.mydelivery.dto.FoodDto;
import com.sparta.mydelivery.dto.RestaurantDto;
import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.Restaurant;
import com.sparta.mydelivery.repository.FoodRepository;
import com.sparta.mydelivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.transaction.Transactional;
import java.net.BindException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public Food saving(List<FoodDto> requestDto, Long restaurantId) {

        List<Food> foods = foodRepository.findAllByRestaurantId(restaurantId);

        if (foods.size() != 0) {
            for (int i = 0; i < requestDto.size(); i++) {
                for(int j = 0; j < foods.size(); j++) {
                    if (requestDto.get(i).getName().equals(foods.get(j).getName())) {
                        throw new TypeMismatchException("중복된 음식입니다");
                    }
                }
            }
            for (int i = 0; i <requestDto.size() - 1; i++) {
                for(int j = i + 1; j < requestDto.size(); j++) {
                    if (requestDto.get(i).getName().equals(requestDto.get(j).getName())) {
                        throw new TypeMismatchException("중복된 음식입니다");
                    }
                }
            }
        }
        for(int k = 0; k < requestDto.size() - 1; k++) {
            if (requestDto.get(k).getPrice() < 100 || requestDto.get(k).getPrice() > 1000000) {
                throw new IllegalArgumentException("허용가격이 아닙니다");
            } else if (requestDto.get(k).getPrice() % 100 != 0) {
                throw new IllegalArgumentException("허용가격이 아닙니다");
            } else {
                Food food = new Food(requestDto.get(k), restaurantId);
                foodRepository.save(food);
            }
        }
        int l = requestDto.size() - 1;
        if (requestDto.get(l).getPrice() < 100 || requestDto.get(l).getPrice() > 1000000) {
            throw new IllegalArgumentException("허용가격이 아닙니다");
        } else if (requestDto.get(l).getPrice() % 100 != 0) {
            throw new IllegalArgumentException("허용가격이 아닙니다");
        } else {
            Food food = new Food(requestDto.get(l), restaurantId);
            return foodRepository.save(food);
        }
    }
}
