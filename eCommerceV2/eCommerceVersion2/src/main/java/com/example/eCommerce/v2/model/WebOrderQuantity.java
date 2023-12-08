package com.example.eCommerce.v2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "web_order_quantity")
public class WebOrderQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "web_order_id", nullable = false)
    private WebOrder webOrder;

    public WebOrder getWebOrder() {
        return webOrder;
    }

    public void setWebOrder(WebOrder webOrder) {
        this.webOrder = webOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}