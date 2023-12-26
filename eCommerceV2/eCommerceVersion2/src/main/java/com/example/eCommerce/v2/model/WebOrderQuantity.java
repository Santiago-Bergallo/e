package com.example.eCommerce.v2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "web_order_quantities")
public class WebOrderQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "web_order_id", nullable = false)
    private WebOrder webOrder;

    @Column(name = "web_order_quantity", nullable = false)
    private Integer WebOrderQuantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return WebOrderQuantity;
    }

    public void setQuantity(Integer quantity) {
        this.WebOrderQuantity = quantity;
    }

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