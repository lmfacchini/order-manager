package com.order.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {

    @Null
    private Long id;

    @NotBlank
    @Size(max = 6)
    private String orderNumber;


    private LocalDateTime created;

    @NotBlank
    @Size(max = 6)
    private String clientCode;

    @Null
    private BigDecimal subTotal;

    @NotEmpty
    @Size(min = 1, max = 10)
    private List<OrderItemDto> items;

    public OrderDto() {
        items = new ArrayList<>();
    }
}
