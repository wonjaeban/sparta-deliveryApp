package com.sparta.mydelivery.controller;

import com.sparta.mydelivery.dto.OrderRequestDto;
import com.sparta.mydelivery.dto.OrderReturnDto;
import com.sparta.mydelivery.model.Order;
import com.sparta.mydelivery.repository.OrderRepository;
import com.sparta.mydelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/order/request")
    public  OrderReturnDto createOrders(@RequestBody OrderRequestDto requestDto) throws Exception {
        return orderService.saving(requestDto);
    }

    @GetMapping("/orders")
    public  List<OrderReturnDto> giveOrders() {
        return orderService.finding();
    }
}
