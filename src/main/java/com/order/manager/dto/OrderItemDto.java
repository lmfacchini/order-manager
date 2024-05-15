package com.order.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @Null
    private Long id;

    @NotBlank
    private String productName;

    @NotNull
    @Positive
    private BigDecimal unitaryValue;

    @Positive
    private BigDecimal amount;

    private BigDecimal subTotal;
}
