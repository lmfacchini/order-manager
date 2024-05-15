package com.order.manager.service;

import com.order.manager.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    OrderDto processOrder(OrderDto order);
    List<OrderDto> findOrders(String orderNumber, LocalDateTime created);

    OrderDto findById(Long id);
}
