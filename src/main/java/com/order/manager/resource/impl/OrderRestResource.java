package com.order.manager.resource.impl;

import com.order.manager.dto.OrderDto;
import com.order.manager.resource.OrderResource;
import com.order.manager.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order/rest")
public class OrderRestResource extends OrderResource {


    public OrderRestResource(OrderService orderService) {
        super(orderService);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order){
        order = super.processOrder(order);
        return ResponseEntity.created(URI.create("/order/"+order.getId())).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> orders(@RequestParam(required = false) String orderNumber,
                                          @RequestParam(required = false) LocalDateTime orderCreated){
        return ResponseEntity.ok(super.searchOrders(orderNumber, orderCreated));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> order(@PathVariable Long id){
        return ResponseEntity.ok(super.findById(id));
    }

}
