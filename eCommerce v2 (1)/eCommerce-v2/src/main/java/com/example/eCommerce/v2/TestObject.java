package com.example.eCommerce.v2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestObject {

    @Id
    private Long id;

    @Column
    private String username;
}
