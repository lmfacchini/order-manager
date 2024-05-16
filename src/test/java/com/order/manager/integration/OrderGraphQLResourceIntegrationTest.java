package com.order.manager.integration;

import com.order.manager.OrderManagerApplication;
import com.order.manager.dto.OrderDto;
import com.order.manager.resource.OrderResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = OrderManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("utest")
public class OrderGraphQLResourceIntegrationTest  {


    @Autowired
    private WebApplicationContext context;



    @Test
    public void createOrder()throws Exception{

        String mutation = "mutation CreateOrder {createOrder(order: { orderNumber: \"213\", created: \"2024-05-15T10:30:00\", clientCode: \"8\", items: [] }) {id}}";

        WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();

        HttpGraphQlTester tester = HttpGraphQlTester.create(client);
        tester.document(mutation).execute();
    }


    @Test
    public void ordersTest()throws Exception{
        String query = "query {orders(orderNumber: \"555\") {id clientCode subTotal}}";
       WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();

        HttpGraphQlTester tester = HttpGraphQlTester.create(client);
        tester.document(query).execute()
                .path("data.orders")
                .hasValue();
    }

    @Test
    public void orderTest()throws Exception{
        String query = "query {order(id: \"1\") {id clientCode subTotal}}";
        WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(context)
                        .configureClient()
                        .baseUrl("/graphql")
                        .build();

        HttpGraphQlTester tester = HttpGraphQlTester.create(client);
        tester.document(query).execute()
                .path("data.order.id")
                .hasValue();
    }
}
