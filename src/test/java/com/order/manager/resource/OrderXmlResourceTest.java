package com.order.manager.resource;

import com.order.manager.OrderManagerApplication;
import com.order.manager.dto.OrderDto;
import com.order.manager.dto.OrderItemDto;
import com.order.manager.resource.impl.OrderXmlResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

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
        order.setOrderNumber("243");
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
    }


    @Test
    public void getOrder(){
        ResponseEntity<OrderDto> response = orderXmlResource.getOrder(1L);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }






}
