package com.order.manager.resource.impl;

import com.order.manager.dto.OrderDto;
import com.order.manager.resource.OrderResource;
import com.order.manager.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class OrderGraphQLResource extends OrderResource {
    public OrderGraphQLResource(OrderService orderService) {
        super(orderService);
    }

    @MutationMapping
    public OrderDto createOrder(@Argument OrderDto order){
        return super.processOrder(order);
    }

    @QueryMapping
    List<OrderDto> orders(@Argument String orderNumber,
                          @Argument LocalDateTime orderCreated){
        return super.searchOrders(orderNumber, orderCreated);
    }

    @QueryMapping
    public OrderDto order(@Argument Long id){
        return super.findById(id);
    }
}
