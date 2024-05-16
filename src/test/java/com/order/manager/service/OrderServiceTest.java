package com.order.manager.service;

import com.order.manager.OrderManagerApplication;
import com.order.manager.domain.Order;
import com.order.manager.domain.OrderItem;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.order.manager.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderServiceTest {

    private static OrderService orderService;
    private static OrderRepository orderRepository;

    @BeforeAll
    public static void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void processOrderTest(){
        OrderDto order = new OrderDto();
        order.setOrderNumber("253");
        order.setClientCode("8");
        order.setItems(List.of(new OrderItemDto(1L,"Product Test", BigDecimal.TEN,BigDecimal.ONE,BigDecimal.TEN)));

        Order ordr = new Order();
        ordr.setId(1L);
        ordr.setOrderNumber("23");
        ordr.setClientCode("8");
        ordr.setItems(List.of(new OrderItem("Product Test", BigDecimal.TEN,BigDecimal.ONE)));

        when(orderRepository.save(any())).thenReturn(ordr);


        order = orderService.processOrder(order);
        assertNotNull(order);
        assertNotNull(order.getId());
    }

    @Test
    public void findOrdersTest(){
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumber("23");
        order.setClientCode("8");
        order.setItems(List.of(new OrderItem("Product Test", BigDecimal.TEN,BigDecimal.ONE)));
        when(orderRepository.findAll()).thenReturn(List.of(order));
        List<OrderDto> result = orderService.findOrders(null, null);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void findByIdTest(){


        Order order = new Order();
        order.setId(1L);
        order.setOrderNumber("23");
        order.setClientCode("8");
        order.setItems(List.of(new OrderItem("Product Test", BigDecimal.TEN,BigDecimal.ONE)));

        Optional<Order> optionalOrder = Optional.of(order);


        when(orderRepository.findById(1L)).thenReturn(optionalOrder);

        OrderDto dto = orderService.findById(1L);

        assertNotNull(dto);
        assertNotNull(dto.getId());

    }
}
