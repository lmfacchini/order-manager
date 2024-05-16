package com.order.manager.integration;

import com.order.manager.OrderManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderRestResourceIntegrationTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createOrderTest()throws Exception{
        String jsonRequest = "{\"orderNumber\": \"5525\",\"created\": \"2024-05-15T10:30:00\",\"clientCode\": \"10\",\"items\": [{\"productName\": \"Product Test\",\"unitaryValue\": 5.67,\"amount\": 3 }]}";
        mockMvc.perform(post("/order/rest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    public void ordersTest()throws Exception{
        mockMvc.perform(get("/order/rest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void orderTest()throws Exception{
        mockMvc.perform(get("/order/rest/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
