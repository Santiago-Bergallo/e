package com.example.eCommerce.v2.repository;

import com.example.eCommerce.v2.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsDao extends ListCrudRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    Optional<Product> findByNameContainsIgnoreCase(String name);


}
