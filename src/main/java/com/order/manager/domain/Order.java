package com.order.manager.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "ordr")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordr_numbr", length = 6, nullable = false)
    private String orderNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "crtd", nullable = false)
    private LocalDateTime created;

    @Column(name = "clnt_cde", length = 6, nullable = false)
    private String clientCode;

    @OneToMany(cascade = PERSIST, fetch = EAGER, mappedBy = "order")
    private List<OrderItem> items;

    @Column(name = "sb_ttl", nullable = false, scale = 2, precision = 7)
    private BigDecimal subTotal;


    public Order() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        created = created == null ? LocalDateTime.now() : created;
        items.forEach(item->item.setOrder(this));
    }


}
