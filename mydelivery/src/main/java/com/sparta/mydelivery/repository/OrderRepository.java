package com.sparta.mydelivery.repository;


import com.sparta.mydelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
