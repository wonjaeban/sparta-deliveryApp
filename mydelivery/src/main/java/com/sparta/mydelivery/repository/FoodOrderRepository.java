package com.sparta.mydelivery.repository;

import com.sparta.mydelivery.model.Food;
import com.sparta.mydelivery.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {

}
