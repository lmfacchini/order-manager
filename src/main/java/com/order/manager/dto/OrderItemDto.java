package com.order.manager.dto;

import jakarta.validation.constraints.*;
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
    @Size(max = 100)
    private String productName;

    @NotNull
    @Positive
    @Max(99999)
    private BigDecimal unitaryValue;

    @Positive
    @Max(99999)
    private BigDecimal amount;

    @Null
    private BigDecimal subTotal;
}
