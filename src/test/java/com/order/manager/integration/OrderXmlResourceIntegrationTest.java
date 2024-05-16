package com.order.manager.integration;

import com.order.manager.OrderManagerApplication;
import com.order.manager.domain.Order;
import com.order.manager.domain.OrderItem;
import org.junit.jupiter.api.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderXmlResourceIntegrationTest {


    @Autowired
    private MockMvc mockMvc;
    @Test
    public void createOrderTest()throws Exception{
        Order order = new Order();
        order.setOrderNumber("22332");

        OrderItem item = new OrderItem();
        item.setAmount(BigDecimal.ONE);
        item.setUnitaryValue(BigDecimal.TEN);
        item.setProductName("Teste Product");

        order.setItems(List.of(item));
        order.setClientCode("3");

        Serializer serializer = new Persister();

        StringWriter writer = new StringWriter();
        serializer.write(order, writer);
        String xml = writer.toString();
        mockMvc.perform(post("/order/xml")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content(xml))
                .andExpect(status().isCreated());
    }


    @Test
    public void ordersTest()throws Exception {
        mockMvc.perform(get("/order/xml")
                        .contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrderTest()throws Exception{
        mockMvc.perform(get("/order/xml/1")
                        .contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk());
    }






}
