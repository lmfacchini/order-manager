package com.order.manager.resource;

import com.order.manager.OrderManagerApplication;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.resource.impl.OrderRestResource;
import com.order.manager.resource.impl.OrderXmlResource;
import com.order.manager.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.PathVariable;

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
public class OrderXmlResourceTest {

    @Autowired
    private OrderXmlResource orderXmlResource;

    @Test
    public void createOrderTest(){
        OrderDto order = new OrderDto();
        order.setOrderNumber("23");
        order.setClientCode("8");

        order.setItems(List.of(new OrderItemDto(1L,"Product Test", BigDecimal.TEN,BigDecimal.ONE,BigDecimal.TEN)));


        ResponseEntity<OrderDto> response = orderXmlResource.createOrder(order);
        assertNotNull(order);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }


    @Test
    public void orders() {
        ResponseEntity<List<OrderDto>> response = orderXmlResource.orders(null, null);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }


    @Test
    public void getOrder(){
        ResponseEntity<OrderDto> response = orderXmlResource.getOrder(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }






}
