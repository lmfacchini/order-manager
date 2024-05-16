package com.order.manager.resource;

import com.order.manager.OrderManagerApplication;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.resource.impl.OrderRestResource;
import com.order.manager.service.OrderService;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderRestResourceTest  {

    @Autowired
    private OrderRestResource orderRestResource;

    @Test
    public void createOrderTest(){
        OrderDto order = new OrderDto();
        order.setOrderNumber("23");
        order.setClientCode("8");

        order.setItems(List.of(new OrderItemDto(1L,"Product Test", BigDecimal.TEN,BigDecimal.ONE,BigDecimal.TEN)));


        ResponseEntity<OrderDto> response = orderRestResource.createOrder(order);
        assertNotNull(order);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }


    @Test
    public void ordersTest(){
        ResponseEntity<List<OrderDto>> response = orderRestResource.orders(null, null);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }


    @Test
    public void orderTest(){
        ResponseEntity<OrderDto> response = orderRestResource.order(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }

}
