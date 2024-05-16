package com.order.manager.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "itm_ordr")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_prdct", length = 100, nullable = false)
    private String productName;

    @Column(name = "un_vle", nullable = false, scale = 2, precision = 7)
    private BigDecimal unitaryValue;

    @Column(name = "amnt", nullable = false, scale = 2, precision = 7)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "ordr_id", nullable = false)
    private Order order;

    @Column(name = "sb_ttl", nullable = false, scale = 2, precision = 7)
    private BigDecimal subTotal;


    public OrderItem(String productName, BigDecimal unitaryValue, BigDecimal amount) {
        this.productName = productName;
        this.unitaryValue = unitaryValue;
        this.amount = amount;
    }

    @PrePersist
    public void prePersist(){
        amount = amount == null ? BigDecimal.ONE : amount;
    }
}
