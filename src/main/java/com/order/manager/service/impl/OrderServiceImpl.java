package com.order.manager.service.impl;

import com.order.manager.BusinessException;
import com.order.manager.domain.Order;
import com.order.manager.domain.OrderItem;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.repository.OrderRepository;
import com.order.manager.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class OrderServiceImpl implements OrderService {

    private static final Set<String> CLIENTS = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public OrderDto processOrder(OrderDto dto) {

        if(!CLIENTS.contains(dto.getClientCode())){
            throw new BusinessException("Client not found!");
        }

        if(repository.findByOrderNumber(dto.getOrderNumber()).isPresent()){
            throw new BusinessException("Order number already exists.!");
        }

        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setClientCode(dto.getClientCode());
        double subtotal = dto.getItems().stream().mapToDouble(item->{
            BigDecimal subTotal = item.getAmount().multiply(item.getUnitaryValue());

            if(item.getAmount().doubleValue() >= 10){
                subTotal = subTotal.subtract(subTotal.multiply(new BigDecimal(0.1)));
            }else if(item.getAmount().doubleValue() > 5){
                subTotal = subTotal.subtract(subTotal.multiply(new BigDecimal(0.05)));
            }
            item.setSubTotal(subTotal);
            return subTotal.doubleValue();
        }).sum();
        order.setItems(dto.getItems().stream().map(this::parse).toList());
        order.setSubTotal(new BigDecimal(subtotal));
        dto.setSubTotal(order.getSubTotal());
        order = repository.save(order);
        dto.setId(order.getId());
        return dto;
    }



    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findOrders(String orderNumber, LocalDateTime created) {
        if(StringUtils.isBlank(orderNumber) && created == null){
            return repository.findAll().stream().map(this::parse).toList();
        }else if(StringUtils.isNotBlank(orderNumber)){
            Optional<Order> optional = repository.findByOrderNumber(orderNumber);
            return optional.isPresent()?List.of(parse(optional.get())):List.of();
        }else{
            return repository.findByCreated(created).map(this::parse).toList();
        }
    }

    @Override
    public OrderDto findById(Long id) {
        Optional<Order> optionalOrder = repository.findById(id);
        if(optionalOrder.isEmpty()){
            return null;
        }
        return parse(optionalOrder.get());
    }

    private OrderDto parse(Order order){
        return new OrderDto(order.getId(), order.getOrderNumber(), order.getCreated(), order.getClientCode(), order.getSubTotal(), order.getItems().stream().map(this::parse).toList());
    }

    private OrderItemDto parse(OrderItem orderItem){
        return new OrderItemDto(orderItem.getId(), orderItem.getProductName(), orderItem.getUnitaryValue(), orderItem.getAmount(), orderItem.getSubTotal());
    }


    private OrderItem parse(OrderItemDto dto) {
        OrderItem item = new OrderItem();
        item.setAmount(dto.getAmount() == null ? BigDecimal.ONE : dto.getAmount());
        item.setProductName(dto.getProductName());
        item.setSubTotal(dto.getSubTotal());
        item.setUnitaryValue(dto.getUnitaryValue());

        return item;
    }


}
