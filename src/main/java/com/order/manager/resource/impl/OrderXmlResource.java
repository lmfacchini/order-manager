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

import static org.springframework.util.MimeTypeUtils.*;


@RestController
@RequestMapping("/order/xml")
public class OrderXmlResource extends OrderResource {

    public static final String NAMESPACE_URI = "http://order.manager.com/service";
    public OrderXmlResource(OrderService orderService) {
        super(orderService);
    }



    @PostMapping(produces={APPLICATION_XML_VALUE, TEXT_XML_VALUE}, consumes = {APPLICATION_XML_VALUE, TEXT_XML_VALUE})
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order){
        order = super.processOrder(order);
        return ResponseEntity.created(URI.create("/order/xml/" + order.getId())).body(order);
    }

    @GetMapping(produces={APPLICATION_XML_VALUE, TEXT_XML_VALUE})
    public ResponseEntity<List<OrderDto>> orders(@RequestParam(required = false) String orderNumber, @RequestParam(required = false) LocalDateTime orderCreated) {
        return ResponseEntity.ok(super.searchOrders(orderNumber, orderCreated));
    }

    @GetMapping(value = "{id}", produces={APPLICATION_XML_VALUE, TEXT_XML_VALUE})
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(super.findById(id));
    }






}
