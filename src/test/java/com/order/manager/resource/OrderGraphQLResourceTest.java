package com.order.manager.resource;

import com.order.manager.OrderManagerApplication;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.resource.impl.OrderGraphQLResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderGraphQLResourceTest  {

    @Autowired
    private OrderGraphQLResource orderGraphQLResource;


    @Test
    public void createOrderTest(){
        OrderDto order = new OrderDto();
        order.setOrderNumber("24443");
        order.setClientCode("8");

        order.setItems(List.of(new OrderItemDto(1L,"Product Test",BigDecimal.TEN,BigDecimal.ONE,BigDecimal.TEN)));


        order = orderGraphQLResource.createOrder(order);
        assertNotNull(order);
        assertNotNull(order.getId());
    }


    @Test
    public void ordersTest(){
        List<OrderDto> result = orderGraphQLResource.searchOrders(null, null);
        assertNotNull(result);
    }


    @Test
    public void orderTest(){
        OrderDto order =  orderGraphQLResource.order(1L);
        assertNotNull(order);
        assertNotNull(order.getId());
    }
}
