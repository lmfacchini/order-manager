package com.order.manager.resource;

import com.order.manager.dto.OrderDto;
import com.order.manager.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

public abstract class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }
    protected OrderDto processOrder(OrderDto order){
        return orderService.processOrder(order);
    }

    protected List<OrderDto> searchOrders(String orderNumber, LocalDateTime orderCreated) {
        return orderService.findOrders(orderNumber, orderCreated);

    }

    protected OrderDto findById(Long id){
        return orderService.findById(id);
    }
}
